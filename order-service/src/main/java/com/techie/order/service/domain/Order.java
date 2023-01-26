package com.techie.order.service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long productId;
    private long quantity;
    private long amount;
    @JsonIgnore
    private PaymentMode paymentMode;
}
