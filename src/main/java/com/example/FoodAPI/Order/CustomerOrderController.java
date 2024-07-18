package com.example.FoodAPI.Order;

import com.example.FoodAPI.Country.Country;
import com.example.FoodAPI.Food.Food;
import com.example.FoodAPI.Food.FoodServices;
import com.example.FoodAPI.Location.Location;
import com.example.FoodAPI.Menu.Menu;
import com.example.FoodAPI.Menu.MenuServices;
import com.example.FoodAPI.Restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class CustomerOrderController {

    @Autowired
    FoodServices foodServices;
    @Autowired
    CustomerOrderService service;

    @RequestMapping(value = "/")
    public List<CustomerOrder> show(){
        return service.Findall();
    }
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public void create(@RequestBody List<ItemsToOrder> param) {
        List<Items> ToBePlaced = new ArrayList<>();
        int amount = 0, count = 0;
        for (ItemsToOrder item : param) {
            String s = item.getFoodId().toString();
            Food f = foodServices.FindOne(s);
            Menu menu = f.getMenu();
            Restaurant Res = menu.getRestaurant();
            Location Loca = Res.getLocation();
            Country Coun = Loca.getCountry();
            Items whatOrdered = new Items(f.getId(), f.getName(), f.getPrice(), item.getCount(), menu.getName(), Res.getName(), Loca.getAddress(), Coun.getName());
            amount += f.getPrice() * item.getCount();
            count += item.getCount();
            ToBePlaced.add(whatOrdered);
        }
        CustomerOrder customerOrder = new CustomerOrder(amount, count, ToBePlaced);
        System.out.println(customerOrder.getId() + "        " + customerOrder.getAmount() + "                     " + count + "            ");
        System.out.println("Order Items:");
        for (Items it : customerOrder.getItems()) {
            System.out.println(it);
        }
        service.create(customerOrder);
        System.out.println(customerOrder.getId() + "        " + customerOrder.getAmount() + "                     " + count + "            ");
    }

    @RequestMapping(value = "/{id}")
    public CustomerOrder showOne(@PathVariable Long id){
        return service.Findone(id);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public void updateIt(@PathVariable Long id){
        CustomerOrder tmp = service.Findone(id);
        tmp.setStatus("PAID");
        service.update(tmp);
    }
}
