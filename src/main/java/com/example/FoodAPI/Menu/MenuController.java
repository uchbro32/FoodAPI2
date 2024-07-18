package com.example.FoodAPI.Menu;

import com.example.FoodAPI.Location.Location;
import com.example.FoodAPI.Location.LocationServices;
import com.example.FoodAPI.Responses;
import com.example.FoodAPI.Restaurant.Restaurant;
import com.example.FoodAPI.Restaurant.RestaurantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    
    @Autowired
    MenuServices tmp;
    @Autowired
    RestaurantServices RS;

    @RequestMapping("/")
    public ResponseEntity<List<Menu>> mi(){
        List<Menu> temp = tmp.FindingAll();
        if(temp.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(temp);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Responses> create(@RequestBody Menu t){
        String id = t.getRestaurantId();
        if(id == null){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        try{
            if(RS.checker(t.getRestaurantId())) return ResponseEntity.badRequest().body(new Responses("Failed", "No Restaurant ID found", null));
            Restaurant obj = RS.FindOne(t.getRestaurantId());
            Menu tt = new Menu(t.getName(), obj);
            tmp.CreatingOBJ(tt);
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
        Menu obj = tmp.FindOne(id);
        return ResponseEntity.ok().body(new Responses("Sucess", "Menu Found", obj));

    }
//
    @RequestMapping(method = RequestMethod.PATCH, value="/")
    public ResponseEntity<Responses>  update(@RequestBody Menu t){
        String sId = t.getId().toString();
        if(t.getId() == null || tmp.checker(sId)){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        String id = t.getRestaurantId();
        if(RS.checker(id)){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No Restaurant Id found", null));
        }
        Restaurant temp = RS.FindOne(t.getRestaurantId());
        Menu obj = new Menu(t.getName(), temp);
        tmp.UpdateTheOne(obj);
        if(obj == null){
            return ResponseEntity.badRequest().body(new Responses("Not Succesful", "Not found Menu", null));
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
