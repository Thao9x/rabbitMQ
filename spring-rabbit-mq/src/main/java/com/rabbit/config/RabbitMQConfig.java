package com.rabbit.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.queue.name}")
	private String queue;

	@Value("${rabbitmq.queue.json.name}")
	private String jsonQueue;

	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.exchange.json.name}")
	private String jsonExchange;

	@Value("${rabbitmq.routing.key}")
	private String routingKey;

	@Value("${rabbitmq.routing.json.key}")
	private String jsonRoutingKey;

	// spring bean for rabbit mq queue
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}

	// spring bean for rabbit mq json queue
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonQueue);
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}

	@Bean
	public TopicExchange jsonExchange() {
		return new TopicExchange(jsonExchange);
	}

	// binding between queue and exchange using routing key
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
	}

	// binding between queue and exchange using json routing key
	@Bean
	public Binding jsonBinding() {
		return BindingBuilder.bind(jsonQueue()).to(jsonExchange()).with(jsonRoutingKey);
	}

	@Bean
	public MessageConverter convert() {
		return new Jackson2JsonMessageConverter();
	}

	// connectionFactory
	// RabbitTemplate
	// RabbitAdmin
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(convert());
		return rabbitTemplate;
	}

}
