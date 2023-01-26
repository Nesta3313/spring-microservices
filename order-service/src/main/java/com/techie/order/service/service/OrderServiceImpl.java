package com.techie.order.service.service;

import com.techie.order.service.domain.Order;
import com.techie.order.service.external.client.ProductService;
import com.techie.order.service.repository.OrderEntity;
import com.techie.order.service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    ProductService productService;
    public long placeOrder(Order order) {

        productService.reduceQuantity(order.getProductId(), order.getQuantity());

        OrderEntity entity = OrderEntity.builder()
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .amount(order.getAmount())
                .orderDate(Instant.now())
                .status("CREATED")
                .build();

        OrderEntity savedOrder = orderRepository.save(entity);
        return savedOrder.getId();
    }

    public List<Order> getOrders() {
        return orderRepository.findAll().stream()
                .map(this::toOrder)
                .toList();
    }

    private Order toOrder(OrderEntity orderEntity) {
        return Order.builder()
                .productId(orderEntity.getProductId())
                .amount(orderEntity.getAmount())
                .quantity(orderEntity.getQuantity())
                .build();
    }
}
