package com.example.activemq.jms;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.util.ErrorHandler;

@Configuration
public class JMSConfig {

	
	String BROKER_URL = "tcp://localhost:61616"; 
	String BROKER_USERNAME = "admin"; 
	String BROKER_PASSWORD = "admin";
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory(){
	    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
	    connectionFactory.setBrokerURL(BROKER_URL);
	    connectionFactory.setUserName(BROKER_PASSWORD);
	    connectionFactory.setPassword(BROKER_USERNAME);
	    return connectionFactory;
	}
	
	// Only required due to defining myFactory in the receiver
	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

		// anonymous class
		factory.setErrorHandler(new ErrorHandler() {
			@Override
			public void handleError(Throwable t) {
				System.err.println("An error has occurred in the transaction: " + t.getMessage());
				t.printStackTrace();
			}
		});

		// lambda function
		//factory.setErrorHandler(t -> System.out.println("An error has occurred in the transaction: " ));

		configurer.configure(factory, connectionFactory);
		return factory;
	}
	
		// Only required due to defining myFactory1 in another receiver
		@Bean
		public JmsListenerContainerFactory<?> myFactory1(ConnectionFactory connectionFactory,
				DefaultJmsListenerContainerFactoryConfigurer configurer) {
			DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

			// anonymous class
			factory.setErrorHandler(new ErrorHandler() {
				@Override
				public void handleError(Throwable t) {
					System.err.println("An error has occurred in the transaction: " + t.getMessage());
					t.printStackTrace();
				}
			});

			// lambda function
			//factory.setErrorHandler(t -> System.out.println("An error has occurred in the transaction: " ));

			configurer.configure(factory, connectionFactory);
			return factory;
		}

	// Serialize message content to json using TextMessage
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
	
}
