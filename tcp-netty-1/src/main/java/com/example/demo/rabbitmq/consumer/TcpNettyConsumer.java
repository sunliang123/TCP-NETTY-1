package com.example.demo.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.rabbitmq.RabbitmqConfiguration;
import com.example.demo.uitls.JacksonUtil;

@Component
@RabbitListener(queues = { RabbitmqConfiguration.tcpNetty })
public class TcpNettyConsumer {

	Logger logger = LoggerFactory.getLogger(getClass());

	@RabbitHandler
	public void handlerMessage(String message) {
		try {
			logger.info("开始消费");
			String msg = JacksonUtil.decode(message, String.class);
			System.out.println(msg);
		} catch (Exception e) {
			logger.info("消费失败");
			e.printStackTrace();
		}
	}

}
