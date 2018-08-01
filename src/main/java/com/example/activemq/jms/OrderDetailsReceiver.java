package com.example.activemq.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.activemq.model.OrderDetails;
import com.example.activemq.repositories.OrderDetailsRepo;

@Component
public class OrderDetailsReceiver {

  @Autowired 
  private OrderDetailsRepo orderDetailsRepo;

  private int count = 1;

  
  @JmsListener(destination = "OrderDetailsQueue", containerFactory = "myFactory1")
  public void receiveOrderDetails (OrderDetails transaction) {
    System.out.println("<" + count + "> Received Order Details event: <" + transaction + ">");
    count++;
    //    throw new RuntimeException();
    orderDetailsRepo.save(transaction);
  }
}
