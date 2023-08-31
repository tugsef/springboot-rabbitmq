package com.tugsef.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tugsef.dto.User;

@Service
public class RappitMQJsonProducer {

	@Value("${tg.rappitmq.exchange.name}")
	private String exchangeName;
	
	@Value("${tg.rappitmq.routing.json.key}")
	private String jsonRoutingKey;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
	
	public void sendJaonMessage(User user) {
		LOGGER.info(String.format("Message send --> %s", user.toString()));
		rabbitTemplate.convertAndSend(exchangeName , jsonRoutingKey , user);
	}
}
