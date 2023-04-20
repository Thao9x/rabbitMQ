package com.rabbit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.dto.User;
import com.rabbit.publisher.RabbitMQJsonProducer;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
	private RabbitMQJsonProducer rabbitMQJsonProducer;

	@PostMapping("/publish")
	public ResponseEntity<User> sendMessage(@RequestBody User user) {
		rabbitMQJsonProducer.sendJsonMessage(user);
		return ResponseEntity.ok().body(user);
	}
}
