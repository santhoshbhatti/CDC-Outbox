package com.infinite.cdcoutbox.outbox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infinite.cdcoutbox.outbox.services.OutboxService;
import com.infinite.cdcoutbox.vos.OutboxVo;

@RestController
public class OutboxController {
	
	@Autowired
	private OutboxService outboxService;
	
	@PostMapping("/v1/{tenantID}/outbox")
	public ResponseEntity<OutboxVo> appendToOutbox(@RequestBody OutboxVo outbox, @PathVariable("tenantID") String tenantID){
		ResponseEntity<OutboxVo> response = null;
		try {
			var savedOutbox = outboxService.appendToOutbox(outbox);
			response = ResponseEntity.status(HttpStatus.CREATED).body(savedOutbox);
		}catch(Exception e) {
			e.printStackTrace();
			response = ResponseEntity.internalServerError().build();
		}
		return response;
	}
}
