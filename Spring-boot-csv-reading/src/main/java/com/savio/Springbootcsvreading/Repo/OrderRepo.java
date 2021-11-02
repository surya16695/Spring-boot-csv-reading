package com.savio.Springbootcsvreading.Repo;

import com.savio.Springbootcsvreading.Model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo  extends JpaRepository<Order, Integer> {
}