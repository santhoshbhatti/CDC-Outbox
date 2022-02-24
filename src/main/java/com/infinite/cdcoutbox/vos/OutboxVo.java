package com.infinite.cdcoutbox.vos;

import lombok.Data;

@Data
public class OutboxVo {
	
	
	private Long id;
	private long aggregateId;
	private String aggregateType;
	private String payload;
	private String eventType;
}
