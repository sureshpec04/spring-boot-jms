package com.example.activemq.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.activemq.model.OrderDetails;
import com.example.activemq.model.OrderTransaction;

@RestController
@RequestMapping("/transaction")
public class OrderTransactionController {

  @Autowired 
  private JmsTemplate jmsTemplate;

  @PostMapping("/send")
  public String sendOrderEvent(@RequestBody OrderTransaction transaction) {
    
	System.out.println("Sending a transaction.");
    jmsTemplate.convertAndSend("OrderTransactionQueue", transaction);
    return "Order Transaction event posted successfully...";
  }
  
  @PostMapping("/order")
  public String send(@RequestBody OrderDetails orderDetails) {
    
	System.out.println("Sending a order...");
    jmsTemplate.convertAndSend("OrderDetailsQueue", orderDetails);
    return "Order Details event posted successfully...";
    
  }
  
  @GetMapping("/")
  public String getTransactions() {
	  return "All Transactions";
  }
}
