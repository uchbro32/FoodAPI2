package com.example.FoodAPI.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CountryServices {

    @Autowired
    CountryRepositry Cr;

    public Country CreatingOBJ(Country tmp){
        return Cr.save(tmp);
//        return;
    }

    public List<Country> FindingAll() {
        List<Country> all = new ArrayList<Country>();
        Cr.findAll().forEach(all::add);
        return all;
    }

    public Country FindOne(String code){
        System.out.println("Inside the Serivice");
        Optional<Country> c = Cr.findById(code);
        return c.orElseThrow();
    }

    public Country UpdateTheOne(Country t) {
        return Cr.save(t);
    }

    public void DeleteOne(String code) {
        Cr.deleteById(code);
        return;
    }
    public boolean checker(String id){
        return !Cr.existsById(id);
    }
}
