package io.khasang.restaurant.dao;

import io.khasang.restaurant.entity.Order;

import java.util.List;

public interface OrderDao extends BasicDao<Order> {

    /**
     * Create order at database
     *
     * @param order - order
     * @return order
     */
    Order addOrder(Order order);

    /**
     * Receive order for table with tableNumber
     *
     * @param tableNumber
     * @return order
     */

    List<Order> getListByTable(int tableNumber);

    /**
     * Set next status order by id
     *
     * @param id order
     * @return order
     */
    Order nextStatus(long id) throws Exception;
}
