package com.example.FoodAPI.Menu;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepositry extends CrudRepository<Menu, Long> {

}
