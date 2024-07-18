package com.example.FoodAPI.Restaurant;

import com.example.FoodAPI.Country.Country;
import com.example.FoodAPI.Country.CountryServices;
import com.example.FoodAPI.Location.Location;
import com.example.FoodAPI.Location.LocationServices;
import com.example.FoodAPI.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    
    @Autowired
    RestaurantServices tmp;
    @Autowired
    LocationServices LS;

    @RequestMapping("/")
    public ResponseEntity<List<Restaurant>> mi(){
        List<Restaurant> temp = tmp.FindingAll();
        if(temp.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(temp);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Responses> create(@RequestBody Restaurant t){
        String id = t.getLocationId();
        if(id == null){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        try{
            if(LS.checker(t.getLocationId())) return ResponseEntity.badRequest().body(new Responses("Failed", "No Location ID found", null));
            Location temp = LS.FindOne(t.getLocationId());
            Restaurant obj = new Restaurant(t.getName(), t.getRating(), temp);
            tmp.CreatingOBJ(obj);
            return ResponseEntity.ok().body(new Responses("Sucess", "Restaurant created successfully", obj));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new Responses("Failed", "Not able to create Restaurant" + e, null));
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Responses> find(@PathVariable String id){
        if(id == null || tmp.checker(id)){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        Restaurant obj = tmp.FindOne(id);
        return ResponseEntity.ok().body(new Responses("Sucess", "Restaurant Found", obj));
    }

    @RequestMapping(method = RequestMethod.PATCH, value="/")
    public ResponseEntity<Responses> update(@RequestBody Restaurant t){
        String sId = t.getId().toString();
        if(t.getId() == null || tmp.checker(sId)){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        String id = t.getLocationId();
        if(LS.checker(id)){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No Location Id found", null));
        }
        Location temp = LS.FindOne(t.getLocationId());
        Restaurant obj = new Restaurant(t.getId(), t.getName(), t.getRating(), temp);
        tmp.UpdateTheOne(obj);
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
