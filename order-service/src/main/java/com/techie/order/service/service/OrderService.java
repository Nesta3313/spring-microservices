package com.techie.order.service.service;

import com.techie.order.service.domain.Order;

import java.util.List;

public interface OrderService {
    long placeOrder(Order order);

    List<Order> getOrders();
}
