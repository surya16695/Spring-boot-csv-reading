package com.savio.Springbootcsvreading.Service;

import javax.transaction.Transactional;

import com.savio.Springbootcsvreading.Model.OrderItem;
import com.savio.Springbootcsvreading.Repo.OrderItemRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderItemService {

    @Autowired
    private OrderItemRepo orderItemsRepository;

    public void addOrderedProducts(OrderItem orderItem) {
        orderItemsRepository.save(orderItem);
    }

}