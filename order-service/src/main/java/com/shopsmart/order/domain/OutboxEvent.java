package com.shopsmart.order.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outbox_event")
public class OutboxEvent {
	@Id
	private UUID id;
	@Column(name = "aggregate_type", nullable = false)
	private String aggregateType;
	@Column(name = "aggregate_id", nullable = false)
	private UUID aggregateId;
	@Column(name = "event_type", nullable = false)
	private String eventType;
	@Column(columnDefinition = "jsonb", nullable = false)
	private String payload;
	@Column(name = "created_at", nullable = false)
	private Instant createdAt = Instant.now();
	@Column(nullable = false)
	private boolean published = false;

	public UUID getId() { return id; }
	public void setId(UUID id) { this.id = id; }
	public String getAggregateType() { return aggregateType; }
	public void setAggregateType(String aggregateType) { this.aggregateType = aggregateType; }
	public UUID getAggregateId() { return aggregateId; }
	public void setAggregateId(UUID aggregateId) { this.aggregateId = aggregateId; }
	public String getEventType() { return eventType; }
	public void setEventType(String eventType) { this.eventType = eventType; }
	public String getPayload() { return payload; }
	public void setPayload(String payload) { this.payload = payload; }
	public Instant getCreatedAt() { return createdAt; }
	public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
	public boolean isPublished() { return published; }
	public void setPublished(boolean published) { this.published = published; }
}
