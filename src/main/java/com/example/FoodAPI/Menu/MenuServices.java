package com.example.FoodAPI.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MenuServices {

    @Autowired
    MenuRepositry Cr;

    public void CreatingOBJ(Menu tmp){
        Cr.save(tmp);
        return;
    }

    public List<Menu> FindingAll() {
        List<Menu> all = new ArrayList<Menu>();
        Cr.findAll().forEach(all::add);
        return all;
    }

    public Menu FindOne(String code){
        Long longValue = Long.parseLong(code);
        Optional<Menu> c = Cr.findById(longValue);
        return c.orElseThrow();
    }

    public void UpdateTheOne(Menu t) {
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
