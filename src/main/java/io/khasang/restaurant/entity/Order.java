package io.khasang.restaurant.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    public static final String STATUS_NEW = "новый";
    public static final String STATUS_WORK = "в работе";
    public static final String STATUS_READY = "готов";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;
    private Date date_ready;
    private String customer;
    private int tableNumber;
    @Column(length=20)
    private String status;
    private String comment;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {
        date = new Date();
        status = STATUS_NEW;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Date getDate_ready() {
        return date_ready;
    }

    public void setDate_ready(Date date_ready) {
        this.date_ready = date_ready;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) throws Exception {
        switch (status) {
            case STATUS_NEW:
                this.status = status;
                break;
            case STATUS_WORK:
                this.status = status;
                break;
            case STATUS_READY:
                this.status = status;
                break;
            default:
                throw new Exception("status error");
        }
    }

    public void nextStatus() throws Exception {
        switch (status) {
            case STATUS_NEW:
                status = STATUS_WORK;
                break;
            case STATUS_WORK:
                status = STATUS_READY;
                date_ready = new Date();
                break;
            default:
                throw new Exception("status error");
        }

    }

}
