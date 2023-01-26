package com.techie.product.service.service;

import com.techie.product.service.advice.ProductServiceCustomException;
import com.techie.product.service.domain.Product;
import com.techie.product.service.repository.ProductEntity;
import com.techie.product.service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public long saveProduct(Product product) {
        log.info("Adding product...");
        ProductEntity savedEntity = repository.save(toEntity(product));
        return savedEntity.getId();
    }

    @Override
    public List<Product> getAllProducts() {
        log.info("Getting all products...");
        Iterable<ProductEntity> productEntities = repository.findAll();

        List<Product> products = new ArrayList<>();

        for(ProductEntity productEntity : productEntities) {
            products.add(toRequest(productEntity));
        }
        return products;
    }

    @Override
    public Product getProductById(long productId) {
        log.info("find by id....");
        return toRequest(repository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(String.format("Product with id %d not found", productId), "PRODUCT_NOT_FOUND")));
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        ProductEntity productEntity = repository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(String.format("Product with id %d not found", productId), "PRODUCT_NOT_FOUND"));

        if(productEntity.getQuantity() < quantity) {
            throw new ProductServiceCustomException(String.format("Quantity requested: %d is less than Inventory quantity: %d ", quantity, productEntity.getQuantity()), "INSUFFICIENT QUANTITY");
        }

        productEntity.setQuantity(productEntity.getQuantity() - quantity);

        repository.save(productEntity);

        log.info("PRODUCT QTY REDUCED BY {}", quantity);
    }

    private ProductEntity toEntity(Product product) {
        return ProductEntity.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }

    private Product toRequest(ProductEntity entity) {
        return Product.builder()
                .productId(entity.getId())
                .name(entity.getName())
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .build();
    }
}
