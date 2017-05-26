package io.khasang.restaurant.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orderes")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long oder_id;
    //    private Date date;
//    private String user;
//    private int tableNumber;
    private String comment;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> dish = new ArrayList<>();

    public Order() {
    }

    public long getOder_id() {
        return oder_id;
    }

    public void setOder_id(long oder_id) {
        this.oder_id = oder_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Dish> getDish() {
        return dish;
    }

    public void setDish(List<Dish> dish) {
        this.dish = dish;
    }
}
