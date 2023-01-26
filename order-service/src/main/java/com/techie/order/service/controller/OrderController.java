package com.techie.order.service.controller;

import com.techie.order.service.domain.Order;
import com.techie.order.service.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @PostMapping
    public ResponseEntity<Long> placeOrder(@RequestBody Order order) {
        long id = orderServiceImpl.placeOrder(order);
        log.info("CREATED ORDER WITH ID: {}", id);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderServiceImpl.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
