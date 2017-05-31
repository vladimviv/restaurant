package io.khasang.restaurant.controller;

import io.khasang.restaurant.entity.Dish;
import io.khasang.restaurant.entity.Order;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/order";
    private final String ADD = "/add";
    private final String UPDATE = "/update";
    private final String GET_ID = "/get/";
    private final String DELETE = "/delete/";
    private final String ALL = "/all";
    
@Test
    public void addOrder() {
        Order order = createOrder();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Order> responseEntity = restTemplate.exchange(
                ROOT + GET_ID + "{id}",
                HttpMethod.GET,
                null,
                Order.class,
                order.getOder_id()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Order resultOrder = responseEntity.getBody();
        assertNotNull(resultOrder);
        assertEquals(order.getComment(), resultOrder.getComment());
    }

@Test
    public void getAllOrders(){
        RestTemplate restTemplate = new RestTemplate();
        createOrder();
        createOrder();

        ResponseEntity<List<Order>> responseEntity = restTemplate.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Order>>() {
                }
        );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void updateOrders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();
        Order order =  createOrder();

        order.setComment("Updated order");
        HttpEntity<Order> httpEntity = new HttpEntity<>(order, httpHeaders);

        Order resultUpdatedOrder = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Order.class).getBody();
        assertNotNull(resultUpdatedOrder);
        assertNotNull(resultUpdatedOrder.getOder_id());
        assertEquals(resultUpdatedOrder.getComment(), resultUpdatedOrder.getComment());
    }

    private Order createOrder() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Order order = orderPrefill();
        HttpEntity<Order> httpEntity = new HttpEntity<>(order, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        Order result = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Order.class).getBody();
        assertNotNull(result);
        assertEquals("Заказ", result.getComment());
        assertNotNull(result.getOder_id());
        return result;
    }

    private Order orderPrefill() {
        Order order = new Order();
        order.setTimestamp(new Date());
        order.setComment("Заказ");
        order.setCustomer("Ivanov");
        order.setTableNumber(3);

        Dish dish = new Dish();
        dish.setName("soupe");
        dish.setAmount(1);

        Dish dish1 = new Dish();
        dish1.setName("bread");
        dish1.setAmount(3);

        order.getDish().add(dish);
        order.getDish().add(dish1);

        return order;
    }

    @Test
    public void deleteOrder(){
        Order order =  createOrder();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "{id}",
                HttpMethod.DELETE,
                null,
                String.class,
                order.getOder_id()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        ResponseEntity<Order> checkOrderExist = restTemplate.exchange(
                ROOT + GET_ID + "{id}",
                HttpMethod.GET,
                null,
                Order.class,
                order.getOder_id()
        );

        assertNull(checkOrderExist.getBody());
    }

}
