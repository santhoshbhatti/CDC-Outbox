package com.infinite.cdcoutbox.outbox.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infinite.cdcoutbox.entities.Outbox;
import com.infinite.cdcoutbox.repository.OutboxRepository;
import com.infinite.cdcoutbox.vos.OutboxVo;

import io.micrometer.core.instrument.util.StringEscapeUtils;
@Service
public class OutboxService {
	
	@Autowired
	OutboxRepository outboxRepository;

	public OutboxVo appendToOutbox(OutboxVo outboxVO) {
		
		Outbox outbox = new Outbox();
		outbox.setAggregateId(outboxVO.getAggregateId());
		outbox.setAggregateType(outboxVO.getAggregateType());
		outbox.setPayload(StringEscapeUtils.escapeJson(outboxVO.getPayload()));
		outbox.setEventType(outboxVO.getEventType());
		var savedOutbox = outboxRepository.save(outbox);
		outboxVO.setId(savedOutbox.getId());
		return outboxVO;
	}

}
