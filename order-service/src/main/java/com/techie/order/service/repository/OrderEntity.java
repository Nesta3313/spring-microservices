package com.techie.order.service.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "tbl_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private long id;
    @Column(name = "product_id")
    private long productId;
    @Column(name = "order_qty")
    private long quantity;
    @Column(name = "order_date")
    private Instant orderDate;
    @Column(name = "order_amt")
    private long amount;
    @Column(name = "order_status")
    private String status;
}
