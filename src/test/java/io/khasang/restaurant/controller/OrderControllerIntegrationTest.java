package io.khasang.restaurant.controller;

import io.khasang.restaurant.entity.Order;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/order";
    private final String ADD = "/add";
    private final String UPDATE = "/update";
    private final String GET_ID = "/get/id/";
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
        assertEquals("Oder1", result.getComment());
        assertNotNull(result.getOder_id());
        return result;
    }

    private Order orderPrefill() {
        Order order = new Order();
        order.setDate(new Date());
        order.setComment("Oder1");
        return order;
    }


}
