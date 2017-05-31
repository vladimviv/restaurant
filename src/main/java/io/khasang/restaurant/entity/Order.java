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

//    @Column(name = "`timestamp`")
//    @Temporal(TemporalType.DATE)
    private Date timestamp;

    private String customer;
    private int tableNumber;
    private String comment;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Dish> dish = new ArrayList<>();

    public Order() {
    }

    public List<Dish> getDish() {
        return dish;
    }

    public void setDish(List<Dish> dish) {
        this.dish = dish;
    }

    public long getOder_id() {
        return oder_id;
    }

    public void setOder_id(long oder_id) {
        this.oder_id = oder_id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
