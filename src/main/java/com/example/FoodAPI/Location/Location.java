package com.example.FoodAPI.Location;
import com.example.FoodAPI.Country.Country;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;


@Entity
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    private String address;
    private String phoneNo ;

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String countryId;

    @ManyToOne // Define the relationship
    @JoinColumn(name = "country_code")
    private Country country;

    public Location() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Location(Long id, String address, String phoneNo, Country country) {
        Id = id;
        this.address = address;
        this.phoneNo = phoneNo;
        this.country = country;
    }
}
