package io.khasang.restaurant.dao;

import io.khasang.restaurant.entity.Order;
import io.khasang.restaurant.entity.OrderItem;

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

    /**
     * Receive orders with status
     *
     * @param status
     * @return list order with status
     */
    List<Order> getListWithStatus(String status);

    /**
     * Receive last order from table
     *
     * @param tableNumber
     * @return last order
     */
    public Order getLastOrder(int tableNumber) throws Exception;

    /**
     * add order item
     *
     * @param id - order id
     * @param item - new order item
     * @return  order with new item
     */
    Order addOrderItem(long id, OrderItem item) throws Exception;
}
