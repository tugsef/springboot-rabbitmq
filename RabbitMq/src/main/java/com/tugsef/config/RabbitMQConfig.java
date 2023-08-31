package com.tugsef.config;

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
	
	@Value("${tg.rappitmq.queue.name}")
	private String queueName;
	
	@Value("${tg.rappitmq.queue.json.name}")
	private String jsonQueueName;
	
	@Value("${tg.rappitmq.exchange.name}")
	private String exchangeName;
	
	@Value("${tg.rappitmq.routing.key}")
	private String routingKey;
	
	@Value("${tg.rappitmq.routing.json.key}")
	private String jsonRoutingKey;

	//spring bean for rabbitmq queue
    @Bean
    Queue queue() {
		return new Queue(queueName);
	}
    
    //spring bean for rabbitmq json queue
    @Bean
    Queue jsonQueue() {
		return new Queue(jsonQueueName);
	}

    //spring rabbitMq exchange
    @Bean
    TopicExchange exchange() {
		return new TopicExchange(exchangeName);
	}
    
    //binding between queue and exchange using routing key
    @Bean
    Binding binding() {
    	return BindingBuilder
    			.bind(queue())
    			.to(exchange())
    			.with(routingKey);
    }
    
  //binding between queue and exchange using routing key
    @Bean
    Binding jsonBinding() {
    	return BindingBuilder
    			.bind(jsonQueue())
    			.to(exchange())
    			.with(jsonRoutingKey);
    }
    
  // Json Converter set
    @Bean
    MessageConverter converter() {
    	return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
    	RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    	rabbitTemplate.setMessageConverter(converter());
    	return rabbitTemplate;
    }
    
	
    // ConnectionFactory
    // RabbitTemplate
    // RabbitAdmin
    // This three configurations to create default springboot
}
