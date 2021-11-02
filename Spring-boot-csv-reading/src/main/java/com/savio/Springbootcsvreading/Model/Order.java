package com.savio.Springbootcsvreading.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import javax.persistence.*;

@Entity
@Table(name="order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    boolean status;

    public Order() {
    }

    public Order(boolean status){
        this.status = status;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isStatus() {
        return this.status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

