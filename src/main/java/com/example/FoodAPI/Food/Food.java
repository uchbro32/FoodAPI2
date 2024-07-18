package com.example.FoodAPI.Food;
import com.example.FoodAPI.Menu.Menu;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;


@Entity
public class Food {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private int price;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String menuId;

    @ManyToOne()
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Food(){

    }
    public Food(String name,  int price , Menu menu) {
        this.name = name;
        this.price = price;
        this.menu = menu;
    }
    public Food(Long Id, String name, int price ,  Menu menu) {
        this.Id = Id;
        this.price = price;
        this.name = name;
        this.menu = menu;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getPrice() {
        return price;
    }

    public String getMenuId() {
        return menuId;
    }

    public Menu getMenu() {
        return menu;
    }
}
