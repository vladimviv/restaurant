package io.khasang.restaurant.service.impl;

import io.khasang.restaurant.dao.OrderDao;
import io.khasang.restaurant.entity.Order;
import io.khasang.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;


    @Override
    public Order addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    @Override
    public List<Order> getOrderList() {
        return orderDao.getList();
    }

    @Override
    public Order deleteOrder(long id) {
        return orderDao.delete(getOrderById(id));
    }

    @Override
    public Order getOrderById(long id) {
        return orderDao.getById(id);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderDao.update(order);
    }

    @Override
    public List<Order> getListOrderByTable(int tableNumber) {
        return orderDao.getListByTable(tableNumber);
    }
}
