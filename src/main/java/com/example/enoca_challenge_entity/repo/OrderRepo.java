package com.example.enoca_challenge_entity.repo;

import com.example.enoca_challenge_entity.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  OrderRepo extends JpaRepository<Order,Long> {
}
