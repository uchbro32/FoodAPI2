package com.example.FoodAPI.Country;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepositry extends CrudRepository<Country, String> {

}
