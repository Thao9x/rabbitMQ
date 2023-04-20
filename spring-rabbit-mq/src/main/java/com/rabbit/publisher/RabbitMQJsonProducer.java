package com.rabbit.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.rabbit.dto.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RabbitMQJsonProducer {

	private Environment environment;

	private static final Logger logger = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

	private RabbitTemplate rabbitTemplate;

	public void sendJsonMessage(User user) {
		logger.info(String.format("Message sent -> %s", user.toString()));
		rabbitTemplate.convertAndSend(environment.getProperty("rabbitmq.exchange.json.name"),
				environment.getProperty("rabbitmq.routing.json.key"), user);
	}
}
