package com.shopsmart.catalog.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {
	@Id
	private UUID id;

	@Column(nullable = false, unique = true, length = 64)
	private String sku;

	@Column(nullable = false, length = 255)
	private String name;

	@Column
	private String description;

	@Column(name = "price_cents", nullable = false)
	private long priceCents;

	@Column(name = "stock_qty", nullable = false)
	private int stockQty;

	@Column(name = "created_at", nullable = false)
	private Instant createdAt = Instant.now();

	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt = Instant.now();

	public UUID getId() { return id; }
	public void setId(UUID id) { this.id = id; }
	public String getSku() { return sku; }
	public void setSku(String sku) { this.sku = sku; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public long getPriceCents() { return priceCents; }
	public void setPriceCents(long priceCents) { this.priceCents = priceCents; }
	public int getStockQty() { return stockQty; }
	public void setStockQty(int stockQty) { this.stockQty = stockQty; }
	public Instant getCreatedAt() { return createdAt; }
	public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
	public Instant getUpdatedAt() { return updatedAt; }
	public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
