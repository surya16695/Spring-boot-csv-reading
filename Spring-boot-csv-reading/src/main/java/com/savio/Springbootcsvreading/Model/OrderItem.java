package com.savio.Springbootcsvreading.Model;

import javax.persistence.*;

@Entity
@Table(name = "orderItems")
public class OrderItem {
    @Id
    String id;

    @Column
    int quantity;

    @Column
    String name;

    @Column
    int orderID;

    public OrderItem(){}

    public OrderItem(String id, int quantity, String name){
        this.id = id;
        this.quantity = quantity;
        this.name = name;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getOrderID() {
        return this.orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

}
