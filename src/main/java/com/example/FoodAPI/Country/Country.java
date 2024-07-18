package com.example.FoodAPI.Country;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Country {
    @Id
    private String code;
    private String name;

    public Country() {
    }

    public Country(String id, String name) {
        this.code = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
