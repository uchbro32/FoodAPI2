package com.example.FoodAPI.Order;

import org.springframework.data.repository.CrudRepository;

public interface CustomerOrderRepositry extends CrudRepository<CustomerOrder, Long> {
}
