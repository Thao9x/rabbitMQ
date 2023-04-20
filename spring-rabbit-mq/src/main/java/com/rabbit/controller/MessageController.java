package com.rabbit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.publisher.RabbitMQProducer;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class MessageController {

	private RabbitMQProducer rabbitMQProducer;

	@GetMapping("/publish")
	public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
		rabbitMQProducer.sendMessage(message);
		return ResponseEntity.ok().body("Message sent to RabbitMQ...");
	}
}
