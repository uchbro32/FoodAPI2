package com.example.FoodAPI.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class LocationServices {

    @Autowired
    LocationRepositry Cr;

    public void CreatingOBJ(Location tmp){
        Cr.save(tmp);
        return;
    }

    public List<Location> FindingAll() {
        List<Location> all = new ArrayList<Location>();
        Cr.findAll().forEach(all::add);
        return all;
    }

    public Location FindOne(String code){
        Optional<Location> c = Cr.findById(code);
        return c.orElseThrow();
    }

    public void UpdateTheOne(Location t) {
        Cr.save(t);
        return;
    }

    public void DeleteOne(String code) {
        Cr.deleteById(code);
        return;
    }

    public boolean checker(String id) {
        return !Cr.existsById(id);
    }
}
