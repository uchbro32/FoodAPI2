package com.example.FoodAPI.Food;

import com.example.FoodAPI.Menu.Menu;
import com.example.FoodAPI.Menu.MenuServices;
import com.example.FoodAPI.Responses;
import com.example.FoodAPI.Restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    
    @Autowired
    FoodServices tmp;
    @Autowired
    MenuServices RS;

    @RequestMapping("/")
    public ResponseEntity<List<Food>> mi(){
        List<Food> temp = tmp.FindingAll();
        if(temp.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(temp);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Responses> create(@RequestBody Food t){
        String id = t.getMenuId();
        if(id == null){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        try{
            if(RS.checker(t.getMenuId())) return ResponseEntity.badRequest().body(new Responses("Failed", "No Restaurant ID found", null));
            Menu obj = RS.FindOne(t.getMenuId());
            Food tt = new Food(t.getName(), t.getPrice(), obj);
            tmp.CreatingOBJ(tt);
            return ResponseEntity.ok().body(new Responses("Sucess", "Food created successfully", obj));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new Responses("Failed", "Not able to create Food" + e, null));
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Responses> find(@PathVariable String id){
        if(id == null || tmp.checker(id)){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        Food obj = tmp.FindOne(id);
        return ResponseEntity.ok().body(new Responses("Sucess", "Menu Found", obj));
    }

    @RequestMapping(method = RequestMethod.PATCH, value="/")
    public ResponseEntity<Responses> update(@RequestBody Food t){
        String sId = t.getId().toString();
        if(t.getId() == null || tmp.checker(sId)){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        String id = t.getMenuId();
        if(RS.checker(id)){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No Menu Id found", null));
        }
        Menu obj = RS.FindOne(t.getMenuId());
        Food tt = new Food(t.getId(), t.getName(), t.getPrice(), obj);
        tmp.UpdateTheOne(tt);
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
