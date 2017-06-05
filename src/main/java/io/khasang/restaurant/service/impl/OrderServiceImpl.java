package io.khasang.restaurant.service.impl;

import io.khasang.restaurant.dao.OrderDao;
import io.khasang.restaurant.entity.Order;
import io.khasang.restaurant.entity.OrderItem;
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
        return orderDao.create(order);
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

    @Override
    public List<Order> getListWithStatus(String status) {
        return orderDao.getListWithStatus(status);
    }

    @Override
    public Order getLastOrder(int tableNumber) throws Exception {
        return orderDao.getLastOrder(tableNumber);
    }

    @Override
    public Order addOrderItem(long id, OrderItem item) throws Exception {
        return orderDao.addOrderItem(id, item);
    }

    @Override
    public Order changeStatus(long id, String status) {
        return orderDao.changeStatus(id, status);
    }
}
