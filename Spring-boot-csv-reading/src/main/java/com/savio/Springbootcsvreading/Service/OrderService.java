package com.savio.Springbootcsvreading.Service;

import com.savio.Springbootcsvreading.Model.Order;
import com.savio.Springbootcsvreading.Repo.OrderRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepository;

    public int saveOrder(Order order){
        orderRepository.save(order);
        return order.getId();
    }
}
