package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.domain.model.Product;

public interface ProductRepositoryPort {

    Product save(Product product);

    boolean existsById(Long id);
}
