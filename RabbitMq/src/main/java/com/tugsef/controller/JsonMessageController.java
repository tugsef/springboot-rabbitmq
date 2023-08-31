package com.tugsef.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tugsef.dto.User;
import com.tugsef.publisher.RappitMQJsonProducer;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/json")
@AllArgsConstructor
public class JsonMessageController {
	
	private RappitMQJsonProducer mqJsonProducer;
	
	@PostMapping
	public ResponseEntity<String> sendMessage(@RequestBody User user){
		mqJsonProducer.sendJaonMessage(user);
		return ResponseEntity.ok("Json message sent to RabbitMQ ...");
	}
}
