package com.techie.product.service.service;

import com.techie.product.service.domain.Product;

import java.util.List;

public interface ProductService {
    long saveProduct(Product product);
    List<Product> getAllProducts();

    Product getProductById(long productId);

    void reduceQuantity(long productId, long quantity);
}

