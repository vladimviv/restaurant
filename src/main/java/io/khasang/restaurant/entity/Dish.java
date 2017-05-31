package io.khasang.restaurant.entity;

import javax.persistence.*;

@Entity
@Table(name="dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long dish_id;
    private String name;
    private int amount;

    public Dish() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDish_id() {
        return dish_id;
    }

    public void setDish_id(long dish_id) {
        this.dish_id = dish_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}

