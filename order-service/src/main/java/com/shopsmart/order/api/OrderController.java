package com.shopsmart.order.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.shopsmart.order.domain.Order;
import com.shopsmart.order.domain.OrderItem;
import com.shopsmart.order.domain.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

interface OrderRepository extends JpaRepository<Order, UUID> {}
interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {}
interface OutboxRepository extends JpaRepository<OutboxEvent, UUID> {}

@RestController
@RequestMapping("/orders")
public class OrderController {
	private final OrderRepository orderRepo;
	private final OrderItemRepository itemRepo;
	private final OutboxRepository outboxRepo;

	public OrderController(OrderRepository orderRepo, OrderItemRepository itemRepo, OutboxRepository outboxRepo) {
		this.orderRepo = orderRepo;
		this.itemRepo = itemRepo;
		this.outboxRepo = outboxRepo;
	}

	@GetMapping("/{id}")
	public Order get(@PathVariable("id") UUID id) {
		return orderRepo.findById(id).orElseThrow();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public CreateOrderResponse create(@Valid @RequestBody CreateOrderRequest body) {
		UUID orderId = UUID.randomUUID();
		Order order = new Order();
		order.setId(orderId);
		order.setUserId(body.userId());
		order.setStatus("PENDING");
		order.setTotalCents(body.totalCents());
		orderRepo.save(order);
		for (CreateOrderItem i : body.items()) {
			OrderItem item = new OrderItem();
			item.setId(UUID.randomUUID());
			item.setOrderId(orderId);
			item.setProductId(UUID.fromString(i.productId()));
			item.setQty(i.qty());
			item.setPriceCents(i.priceCents());
			itemRepo.save(item);
		}
		OutboxEvent ev = new OutboxEvent();
		ev.setId(UUID.randomUUID());
		ev.setAggregateType("Order");
		ev.setAggregateId(orderId);
		ev.setEventType("OrderCreated");
		ev.setPayload("{\"orderId\":\"" + orderId + "\"}");
		outboxRepo.save(ev);
		return new CreateOrderResponse(orderId.toString());
	}

	public record CreateOrderItem(@NotBlank String productId, int qty, long priceCents) {}
	public record CreateOrderRequest(@NotBlank String userId, long totalCents, List<CreateOrderItem> items) {}
	public record CreateOrderResponse(String orderId) {}
}
