package com.rabbit.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rabbit.dto.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RabbitMQJsonConsumer {
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

	@RabbitListener(queues = { "${rabbitmq.queue.json.name}" })
	public void consumeJson(User user) {
		logger.info(String.format("Recieved message -> %s", user.toString()));
	}
}
