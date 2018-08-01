package com.example.activemq.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.activemq.model.OrderTransaction;
import com.example.activemq.repositories.OrderTransactionRepository;

@Component
public class OrderTransactionReceiver {

  @Autowired 
  private OrderTransactionRepository transactionRepository;
  
  private int count = 1;

  @JmsListener(destination = "OrderTransactionQueue", containerFactory = "myFactory")
  public void receiveOrderTransaction (OrderTransaction transaction) {
    System.out.println("<" + count + "> Received Order Transaction:   <" + transaction + ">");
    count++;
    transactionRepository.save(transaction);
    
  }
  
  
 
}
