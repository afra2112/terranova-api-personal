package com.terranova.api.v1.appointment.domain.port.out;

import com.terranova.api.v1.appointment.domain.model.product.ProductResponse;
import com.terranova.api.v1.appointment.infrastructure.adapter.out.product.dto.ProductMetadataResponse;

import java.util.List;
import java.util.Optional;

public interface ProductServicePort {

    Optional<ProductResponse> getProductById(Long productId);

    List<ProductMetadataResponse> getProductMetadataById(List<Long> productIds);
}
