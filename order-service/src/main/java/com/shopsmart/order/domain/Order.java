package com.shopsmart.order.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	private UUID id;
	@Column(name = "user_id", nullable = false)
	private String userId;
	@Column(nullable = false)
	private String status;
	@Column(name = "total_cents", nullable = false)
	private long totalCents;
	@Column(name = "created_at", nullable = false)
	private Instant createdAt = Instant.now();
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt = Instant.now();

	public UUID getId() { return id; }
	public void setId(UUID id) { this.id = id; }
	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	public long getTotalCents() { return totalCents; }
	public void setTotalCents(long totalCents) { this.totalCents = totalCents; }
	public Instant getCreatedAt() { return createdAt; }
	public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
	public Instant getUpdatedAt() { return updatedAt; }
	public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
