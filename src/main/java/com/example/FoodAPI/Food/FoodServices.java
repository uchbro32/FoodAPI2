package com.example.FoodAPI.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FoodServices {

    @Autowired
    FoodRepositry Cr;

    public void CreatingOBJ(Food tmp){
        Cr.save(tmp);
        return;
    }

    public List<Food> FindingAll() {
        List<Food> all = new ArrayList<Food>();
        Cr.findAll().forEach(all::add);
        return all;
    }

    public Food FindOne(String code){
        Long longValue = Long.parseLong(code);
        Optional<Food> c = Cr.findById(longValue);
        return c.orElseThrow();
    }

    public void UpdateTheOne(Food t) {
        Cr.save(t);
        return;
    }

    public void DeleteOne(String code) {
        Long longValue = Long.parseLong(code);
        Cr.deleteById(longValue);
        return;
    }
    public void delete(Long dd){
        Cr.deleteById(dd);
    }

    public boolean checker(String id) {
        Long num = Long.parseLong(id);
        return !Cr.existsById(num);
    }
}
