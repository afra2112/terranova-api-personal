package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.domain.model.Product;

import java.util.Optional;

public interface ProductRepositoryPort {

    Product save(Product product);

    Optional<Product> getById(Long productId);

    boolean existsById(Long id);
}
