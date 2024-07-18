package com.example.FoodAPI.Country;

import java.util.*;

import com.example.FoodAPI.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    CountryServices tmp;
//ResponseEntity
    @RequestMapping("/")
    public ResponseEntity<List<Country>> mi(){
        List<Country> temp = tmp.FindingAll();
        if(temp.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(temp);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Responses> create(@RequestBody Country t){
        if(t.getCode() == null){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        try{
            Country obj = tmp.CreatingOBJ(t);
            return ResponseEntity.ok().body(new Responses("Sucess", "COuntry created successfully", obj));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new Responses("Failed", "Not able to create Country", null));
        }
    }
    @RequestMapping("/{id}")
    public ResponseEntity<Responses> find(@PathVariable String id){
        if(id == null || tmp.checker(id)){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        Country obj = tmp.FindOne(id);
        return ResponseEntity.ok().body(new Responses("Sucess", "Country Found", obj));
    }

    @RequestMapping(method = RequestMethod.PATCH, value="/")
    public ResponseEntity<Responses> update(@RequestBody Country t){
        if(t.getCode() == null || tmp.checker(t.getCode())){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        Country obj = tmp.UpdateTheOne(t);
        if(obj == null){
            return ResponseEntity.badRequest().body(new Responses("Not Succesful", "Not found Country", null));
        }
        return ResponseEntity.ok().body(new Responses("Sucess", "Updated your data", obj));
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{code}")
    public ResponseEntity<Responses> delete(@PathVariable String code){
//        tmp.DeleteOne(code);
        if(code == null || tmp.checker(code)){
            return ResponseEntity.badRequest().body(new Responses("Failed", "No ID found", null));
        }
        return ResponseEntity.ok().body(new Responses("Sucess", "Deleted your data", null));
    }
}
