package com.rabbit.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RabbitMQProducer {

	private Environment environment;

	private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducer.class);

	private RabbitTemplate rabbitTemplate;

	public void sendMessage(String message) {
		logger.info(String.format("Message sent -> %s", message));
		rabbitTemplate.convertAndSend(environment.getProperty("rabbitmq.exchange.name"),
				environment.getProperty("rabbitmq.routing.key"), message);
	}
}
