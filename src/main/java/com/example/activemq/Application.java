package com.example.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import com.example.activemq.model.OrderTransaction;

import lombok.extern.slf4j.Slf4j;

@EnableJms
@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

	 @Autowired 
	  private JmsTemplate jmsTemplate;
	 
	 
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sending a transaction.");
		OrderTransaction transaction = new OrderTransaction();
		transaction.setSender("Test Sender");
		transaction.setReceiver("Test Receiver");
		transaction.setAmount(150);
	    jmsTemplate.convertAndSend("OrderTransactionQueue", transaction);
	   log.info("Order Transaction event posted successfully...");
		
	}

	
	
}
