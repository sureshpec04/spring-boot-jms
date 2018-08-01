package com.example.activemq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.activemq.model.OrderTransaction;

@Repository
public interface OrderTransactionRepository extends JpaRepository<OrderTransaction, Long> {
	
}
