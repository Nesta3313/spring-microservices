package com.techie.product.service.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private long productId;
    private String name;
    private long quantity;
    private long price;
}
