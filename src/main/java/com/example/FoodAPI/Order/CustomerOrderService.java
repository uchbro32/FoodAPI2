package com.example.FoodAPI.Order;

import com.example.FoodAPI.Restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerOrderService {
    @Autowired
    CustomerOrderRepositry repo;

    public  List<CustomerOrder> Findall() {
        List<CustomerOrder> all = new ArrayList<CustomerOrder>();
        repo.findAll().forEach( all :: add);
        return all;
    }


    public void create(CustomerOrder obj){
        repo.save(obj);
    }

    public CustomerOrder Findone(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public void update(CustomerOrder obj) {
        repo.save(obj);
    }
}
