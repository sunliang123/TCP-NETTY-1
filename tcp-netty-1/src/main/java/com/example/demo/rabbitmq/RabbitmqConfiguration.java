package com.example.demo.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitmqConfiguration {

	Logger logger = LoggerFactory.getLogger(getClass());

	public static final String tcpNetty = "tcp-netty-1";

	@Autowired
	private ConnectionFactory connectionFactory;

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplate() {
		logger.info("host,username:{}{}", connectionFactory.getHost(), connectionFactory.getUsername());
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		return rabbitTemplate;
	}

	@Bean(name = { "quoteListenerContainerFactory" })
	public SimpleRabbitListenerContainerFactory quoteListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrentConsumers(10);
		factory.setMaxConcurrentConsumers(50);
		return factory;
	}

	@Bean(name = { "deleteQuoteListenerContainerFactory" })
	public SimpleRabbitListenerContainerFactory deleteQuoteListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrentConsumers(10);
		factory.setMaxConcurrentConsumers(25);
		return factory;
	}

	@Bean(name = { "historicalDataListenerContainerFactory" })
	public SimpleRabbitListenerContainerFactory historicalDataListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrentConsumers(2);
		factory.setMaxConcurrentConsumers(5);
		return factory;
	}

	/**
	 * 创建 demo队列
	 */
	@Bean
	public Queue commodityQueue() {
		logger.info("开始创建队列");
		return new Queue(tcpNetty);
	}
}
