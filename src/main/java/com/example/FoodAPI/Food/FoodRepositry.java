package com.example.FoodAPI.Food;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepositry extends CrudRepository<Food, Long> {

}
