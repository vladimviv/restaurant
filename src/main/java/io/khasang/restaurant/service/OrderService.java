package io.khasang.restaurant.service;


import io.khasang.restaurant.entity.Order;

import java.util.List;

public interface OrderService {

    /**
     * Create order at database
     *
     * @param order - order for creation
     * @return order
     */
    Order addOrder(Order order);

    /**
     * Receive all orders
     *
     * @return all orders from database
     */
    List<Order> getOrderList();

    /**
     * Delete specified order by id
     *
     * @param id - id of order for deleting
     */
    Order deleteOrder(long id);

    /**
     * Receive order by id
     *
     * @param id - id order
     * @return order
     */
    Order getOrderById(long id);

    /**
     * Update order
     *
     * @param order - order from request for update
     * @return updated order
     */
    Order updateOrder(Order order);

    /**
     * Receive all orders for table with number
     *
     * @param tableNumber
     * @return order for table with tableNumber
     */
    List<Order> getListOrderByTable(int tableNumber );

    /**
     * Set next status order by id
     *
     * @param id - id order
     * @return order with status
     */
    Order nextStatus(long id) throws Exception;
}
