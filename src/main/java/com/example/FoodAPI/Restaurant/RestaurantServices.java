package com.example.FoodAPI.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RestaurantServices {

    @Autowired
    RestaurantRepositry Cr;

    public void CreatingOBJ(Restaurant tmp){
        Cr.save(tmp);
        return;
    }

    public List<Restaurant> FindingAll() {
        List<Restaurant> all = new ArrayList<Restaurant>();
        Cr.findAll().forEach(all::add);
        return all;
    }

    public Restaurant FindOne(String code){
        Long longValue = Long.parseLong(code);
        Optional<Restaurant> c = Cr.findById(longValue);
        return c.orElseThrow();
    }

    public void UpdateTheOne(Restaurant t) {
        Cr.save(t);
        return;
    }

    public void DeleteOne(String code) {
        Long longValue = Long.parseLong(code);
        Cr.deleteById(longValue);
        return;
    }

    public boolean checker(String id) {
        Long num = Long.parseLong(id);
        return !Cr.existsById(num);
    }
}
