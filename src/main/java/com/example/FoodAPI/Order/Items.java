package com.example.FoodAPI.Order;

import jakarta.persistence.Embeddable;

@Embeddable
public class Items {
    private Long Id;
    private String foodName;
    private int foodPrice;
    private int count;
    private String menu;
    private String restaurant_name;
    private String restaurant_address;
    private String restaurant_country;

    public Items() {
    }

    public Items(Long id, String foodName, int foodPrice, int count, String menu, String restaurant_name, String restaurant_address, String restaurant_country) {
        Id = id;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.count = count;
        this.menu = menu;
        this.restaurant_name = restaurant_name;
        this.restaurant_address = restaurant_address;
        this.restaurant_country = restaurant_country;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + Id +
                ", foodName='" + foodName + '\'' +
                ", foodPrice=" + foodPrice +
                ", count=" + count +
                ", menu='" + menu + '\'' +
                ", restaurant_name='" + restaurant_name + '\'' +
                ", restaurant_address='" + restaurant_address + '\'' +
                ", restaurant_country='" + restaurant_country + '\'' +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getRestaurant_address() {
        return restaurant_address;
    }

    public void setRestaurant_address(String restaurant_address) {
        this.restaurant_address = restaurant_address;
    }

    public String getRestaurant_country() {
        return restaurant_country;
    }

    public void setRestaurant_country(String restaurant_country) {
        this.restaurant_country = restaurant_country;
    }
}
