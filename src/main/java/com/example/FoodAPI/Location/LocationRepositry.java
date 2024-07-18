package com.example.FoodAPI.Location;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepositry extends CrudRepository<Location, String> {

}
