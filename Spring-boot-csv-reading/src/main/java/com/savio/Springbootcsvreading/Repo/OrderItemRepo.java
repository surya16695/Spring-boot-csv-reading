package com.savio.Springbootcsvreading.Repo;

import com.savio.Springbootcsvreading.Model.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo  extends JpaRepository<OrderItem, String> {
}
