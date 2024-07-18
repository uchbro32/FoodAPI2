package com.example.FoodAPI.Restaurant;
import com.example.FoodAPI.Location.Location;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;


@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private float rating;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String locationId;

    @ManyToOne()
    @JoinColumn(name = "location_id")
    private Location location;

    public Restaurant() {

    }

    public Restaurant(String name, float rating, Location location) {
        this.name = name;
        this.rating = rating;
        this.location = location;
    }

    public Long getId() {
        return Id;
    }

    public Restaurant(Long id, String name, float rating, Location location) {
        Id = id;
        this.name = name;
        this.rating = rating;
        this.location = location;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


}
