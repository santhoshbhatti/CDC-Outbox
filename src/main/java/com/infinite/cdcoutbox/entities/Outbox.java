package com.infinite.cdcoutbox.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "outbox")
public class Outbox {
	
	@Id
    @GeneratedValue    
	@Column(name = "id")
	private Long id;
	
	@Column(name = "aggregateid")
	private long aggregateId;
	
	@Column(name = "aggregatetype")
	private String aggregateType;
	
	@Column(name = "payload",columnDefinition = "text(65535)")
	private String payload;
	
	@Column(name = "eventtype")
	private String eventType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(long aggregateId) {
		this.aggregateId = aggregateId;
	}

	public String getAggregateType() {
		return aggregateType;
	}

	public void setAggregateType(String aggregateType) {
		this.aggregateType = aggregateType;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	

}
