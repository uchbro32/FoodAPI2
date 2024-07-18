package com.example.FoodAPI.Order;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status = "PENDING";
    private int amount;
    private int count;
    @ElementCollection
    private List<Items> items;

    public CustomerOrder() {
//        long kid = (long) (Math.random() * 1000);
//        setId(kid);
    }


    public CustomerOrder(int amount, int count, List<Items> items) {
//        long kid = (long) (Math.random() * 1000);
//        setId(kid);
        this.amount = amount;
        this.count = count;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
