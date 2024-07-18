package com.example.FoodAPI.Location;

import com.example.FoodAPI.Country.Country;
import com.example.FoodAPI.Country.CountryServices;
import com.example.FoodAPI.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationServices tmp;
    @Autowired
    CountryServices countryServices;

    @RequestMapping("/")
    public ResponseEntity<List<Location>> mi() {
        List<Location> temp = tmp.FindingAll();
        if(temp.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(temp);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Responses> create(@RequestBody Location t){
        String id = t.getCountryId();
        if(id == null){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        try{
            Country obj = countryServices.FindOne(id);
            Location s = new Location(t.getId(), t.getAddress(), t.getPhoneNo(), obj);
            tmp.CreatingOBJ(s);
            return ResponseEntity.ok().body(new Responses("Sucess", "Location created successfully", obj));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new Responses("Failed", "Not able to create Location" + e, null));
        }
    }
    @RequestMapping("/{id}")
    public ResponseEntity<Responses> find(@PathVariable String id){
        if(id == null || tmp.checker(id)){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        Location obj = tmp.FindOne(id);
        return ResponseEntity.ok().body(new Responses("Sucess", "Location Found", obj));
    }

    @RequestMapping(method = RequestMethod.PATCH, value="/")
    public ResponseEntity<Responses> update(@RequestBody Location t){
        String sId = t.getId().toString();
        if(t.getId() == null || tmp.checker(sId)){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        String id = t.getCountryId();
        if(countryServices.checker(id)){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No Country Id found", null));
        }
        Country obj = countryServices.FindOne(id);
        Location s = new Location(t.getId(), t.getAddress(), t.getPhoneNo(), obj);
        tmp.UpdateTheOne(s);
        if(obj == null){
            return ResponseEntity.badRequest().body(new Responses("Not Succesful", "Not found Country", null));
        }
        return ResponseEntity.ok().body(new Responses("Sucess", "Updated your data", obj));
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{code}")
    public ResponseEntity<Responses> delete(@PathVariable String code){
        if(code == null || tmp.checker(code)){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        tmp.DeleteOne(code);
        return ResponseEntity.ok().body(new Responses("Sucess", "Deleted your data", null));
    }
}
