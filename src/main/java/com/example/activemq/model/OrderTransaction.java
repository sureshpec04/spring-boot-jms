package com.example.activemq.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//@ToString(exclude = "id")
@NoArgsConstructor
@Table(name = "order_transaction")
public class OrderTransaction implements Serializable{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String sender;
  private String receiver;
  private double amount;

  public OrderTransaction(final String sender, final String receiver, final double amount) {
    this.sender = sender;
    this.receiver = receiver;
    this.amount = amount;
  }
}
