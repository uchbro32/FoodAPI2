package com.example.FoodAPI.Menu;
import com.example.FoodAPI.Location.Location;
import com.example.FoodAPI.Restaurant.Restaurant;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;


@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private boolean active = true;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String restaurantId;

    @ManyToOne()
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Menu(){

    }
    public Menu(String name, Restaurant restaurant) {
        this.name = name;
        this.restaurant = restaurant;
    }

    public Long getId() {
        return Id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant( Restaurant  restaurant) {
        this.restaurant = restaurant;
    }
}
