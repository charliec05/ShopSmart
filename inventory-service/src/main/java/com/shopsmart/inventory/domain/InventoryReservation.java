package com.shopsmart.inventory.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "inventory_reservation")
public class InventoryReservation {
	@Id
	private UUID id;
	@Column(name = "order_id", nullable = false)
	private UUID orderId;
	@Column(name = "product_id", nullable = false)
	private UUID productId;
	@Column(nullable = false)
	private int qty;
	@Column(nullable = false)
	private String status;
	@Column(name = "created_at", nullable = false)
	private Instant createdAt = Instant.now();

	public UUID getId() { return id; }
	public void setId(UUID id) { this.id = id; }
	public UUID getOrderId() { return orderId; }
	public void setOrderId(UUID orderId) { this.orderId = orderId; }
	public UUID getProductId() { return productId; }
	public void setProductId(UUID productId) { this.productId = productId; }
	public int getQty() { return qty; }
	public void setQty(int qty) { this.qty = qty; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	public Instant getCreatedAt() { return createdAt; }
	public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
