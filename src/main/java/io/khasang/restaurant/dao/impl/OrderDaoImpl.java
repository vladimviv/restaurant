package io.khasang.restaurant.dao.impl;

import io.khasang.restaurant.dao.OrderDao;
import io.khasang.restaurant.entity.Order;
import io.khasang.restaurant.entity.OrderItem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    @Override
    public List<Order> getListByTable(int tableNumber) {
        List<Order> orders = (List<Order>) sessionFactory.getCurrentSession()
                .createQuery("from Order as o where o.tableNumber = ?")
                .setParameter(0, tableNumber).list();
        return orders;
    }

    @Override
    public Order nextStatus(long id) throws Exception {
        Order order = getById(id);
        order.nextStatus();
        return order;
    }

    @Override
    public List<Order> getListWithStatus(String status) {
        List<Order> orders = (List<Order>) sessionFactory.getCurrentSession()
                .createQuery("from Order as o where o.status = ?")
                .setParameter(0, status).list();
        return orders;
    }

    @Override
    public Order getLastOrder(int tableNumber) throws Exception {
        List<Order> orders = (List<Order>) sessionFactory.getCurrentSession()
                .createQuery("from Order as o where o.tableNumber = ? and o.date in (select MAX(date) from Order where tableNumber = ?)")
                .setParameter(0, tableNumber)
                .setParameter(1, tableNumber).list();
        if (orders.size()>0)
        {
            return orders.get(0);
        }
        throw new Exception("order not found");
    }

    @Override
    public Order addOrderItem(long id, OrderItem item) throws Exception {
        Order order = getById(id);
        if (order == null)
        {
            throw new Exception("order not found");
        }
        order.getItems().add(item);
        return order;
    }
}
