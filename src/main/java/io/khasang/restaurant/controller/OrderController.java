package io.khasang.restaurant.controller;

import io.khasang.restaurant.entity.Order;
import io.khasang.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Order addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Order getOrderById(@PathVariable(value = "id") String id){
        return orderService.getOrderById(Long.parseLong(id));
    }

    @RequestMapping(value = "/table/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getListOrderByTable(@PathVariable(value = "id") String id){
        return orderService.getListOrderByTable(Integer.parseInt(id));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getOrderList(){
        return orderService.getOrderList();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Order deleteOrder(@PathVariable(value = "id") String id){
        return orderService.deleteOrder(Long.parseLong(id));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Order updateOrder(@RequestBody Order order){
        orderService.updateOrder(order);
        return order;
    }



}
