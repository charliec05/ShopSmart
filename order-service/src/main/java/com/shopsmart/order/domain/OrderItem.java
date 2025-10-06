package com.shopsmart.order.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "order_item")
public class OrderItem {
	@Id
	private UUID id;
	@Column(name = "order_id", nullable = false)
	private UUID orderId;
	@Column(name = "product_id", nullable = false)
	private UUID productId;
	@Column(nullable = false)
	private int qty;
	@Column(name = "price_cents", nullable = false)
	private long priceCents;

	public UUID getId() { return id; }
	public void setId(UUID id) { this.id = id; }
	public UUID getOrderId() { return orderId; }
	public void setOrderId(UUID orderId) { this.orderId = orderId; }
	public UUID getProductId() { return productId; }
	public void setProductId(UUID productId) { this.productId = productId; }
	public int getQty() { return qty; }
	public void setQty(int qty) { this.qty = qty; }
	public long getPriceCents() { return priceCents; }
	public void setPriceCents(long priceCents) { this.priceCents = priceCents; }
}
