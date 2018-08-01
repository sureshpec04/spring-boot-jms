package com.example.activemq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.activemq.model.OrderDetails;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Long> {
	
}
