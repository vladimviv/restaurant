package io.khasang.restaurant.dao;

import io.khasang.restaurant.entity.Order;

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

    Order getByTable(int tableNumber);
}
