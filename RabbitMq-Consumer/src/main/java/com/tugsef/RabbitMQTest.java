package com.tugsef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.tugsef.dto.User;

@Service
public class RabbitMQTest {

	Logger LOGGER = LoggerFactory.getLogger(RabbitMQTest.class);
	
	@RabbitListener(queues = "${tg.rappitmq.queue.name}")
	public void consume(String message) {
			LOGGER.info("Received message -> %s" + message);
	}
	
	@RabbitListener(queues = "${tg.rappitmq.queue.json.name}")
	public void JsonConsume(User user) {
			LOGGER.info("Received message -> %s" + user);
	}
}
