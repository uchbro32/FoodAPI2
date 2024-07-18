package com.example.FoodAPI.Restaurant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepositry extends CrudRepository<Restaurant, Long> {

}
