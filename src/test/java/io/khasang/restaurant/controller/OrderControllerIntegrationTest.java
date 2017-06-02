package io.khasang.restaurant.controller;

import io.khasang.restaurant.entity.OrderItem;
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
    private final String TABLE = "/table/";
    private final String ALL = "/all";
    private final String NEXTSTATUS = "/nextstatus/";

@Ignore
@Test
    public void addOrder() {
        Order order = createOrder();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Order> responseEntity = restTemplate.exchange(
                ROOT + GET_ID + "{id}",
                HttpMethod.GET,
                null,
                Order.class,
                order.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Order resultOrder = responseEntity.getBody();
        assertNotNull(resultOrder);
        assertEquals(order.getComment(), resultOrder.getComment());
    }

    @Ignore
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

    @Ignore
    @Test
    public void getAllOrdersByTable(){
        RestTemplate restTemplate = new RestTemplate();
        Order order = createOrder();

        ResponseEntity<List<Order>> responseEntity = restTemplate.exchange(
                ROOT + TABLE+ "{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Order>>() {
                },
                order.getTableNumber()

        );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Ignore
    @Test
    public void updateOrders(){
        Order order =  createOrder();
        order.setComment("Updated order");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Order> httpEntity = new HttpEntity<>(order, httpHeaders);

        Order resultUpdatedOrder = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Order.class).getBody();
        assertNotNull(resultUpdatedOrder);
        assertNotNull(resultUpdatedOrder.getId());
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
                HttpMethod.POST,
                httpEntity,
                Order.class).getBody();
        assertNotNull(result);
        assertEquals("Заказ", result.getComment());
        assertNotNull(result.getId());
        return result;
    }

    private Order orderPrefill() {
        Order order = new Order();
        order.setDate(new Date());
        order.setComment("Заказ");
        order.setCustomer("Ivanov");
        order.setTableNumber(3);

        OrderItem orderItem = new OrderItem();
        orderItem.setName("soup");
        orderItem.setAmount(1);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setName("bread");
        orderItem1.setAmount(3);

        order.getItems().add(orderItem);
        order.getItems().add(orderItem1);

        return order;
    }

    @Ignore
    @Test
    public void deleteOrder(){
        Order order =  createOrder();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "{id}",
                HttpMethod.DELETE,
                null,
                String.class,
                order.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        ResponseEntity<Order> checkOrderExist = restTemplate.exchange(
                ROOT + GET_ID + "{id}",
                HttpMethod.GET,
                null,
                Order.class,
                order.getId()
        );

        assertNull(checkOrderExist.getBody());
    }
//    @Ignore
    @Test
    public void nextStatus() {
        Order order =  createOrder();
        try {
            order.nextStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Order> httpEntity = new HttpEntity<>(order, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        Order resultUpdatedOrder = restTemplate.exchange(
                ROOT +  NEXTSTATUS + "{id}",
                HttpMethod.POST,
                httpEntity,
                Order.class,
                order.getId()).getBody();
        assertNotNull(resultUpdatedOrder);
        assertNotNull(resultUpdatedOrder.getId());
        assertEquals(order.getStatus(), resultUpdatedOrder.getStatus());



    }

}
