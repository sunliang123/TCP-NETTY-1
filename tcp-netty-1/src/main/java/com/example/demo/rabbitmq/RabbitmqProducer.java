package com.example.demo.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.uitls.JacksonUtil;

@Component
public class RabbitmqProducer {

	@Autowired
	private RabbitTemplate template;

	public void sendMessage(String queueName, Object message) {
		template.convertAndSend(queueName, JacksonUtil.encode(message));
	}

}