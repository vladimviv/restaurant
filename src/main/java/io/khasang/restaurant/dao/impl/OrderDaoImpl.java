package io.khasang.restaurant.dao.impl;

import io.khasang.restaurant.dao.OrderDao;
import io.khasang.restaurant.entity.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDaoImpl extends BasicDaoImpl<Order> implements OrderDao {
    @Autowired
    SessionFactory sessionFactory;

    public OrderDaoImpl(Class<Order> entityClass) {
        super(entityClass);
    }

    @Override
    public Order create(Order entity) {
        return null;
    }

    @Override
    public Order addOrder(Order order) {
       sessionFactory.getCurrentSession().save(order);
       return order;
    }

}
