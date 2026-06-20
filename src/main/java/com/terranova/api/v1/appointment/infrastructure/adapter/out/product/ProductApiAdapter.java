package com.terranova.api.v1.appointment.infrastructure.adapter.out.product;

import com.terranova.api.v1.appointment.domain.model.product.ProductResponse;
import com.terranova.api.v1.appointment.domain.port.out.ProductServicePort;
import com.terranova.api.v1.appointment.infrastructure.adapter.out.product.dto.ProductMetadataResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductApiAdapter implements ProductServicePort {

    private final ProductFeignClient productFeignClient;

    @Override
    public Optional<ProductResponse> getProductById(Long productId) {
        return Optional.of(productFeignClient.getProductById(productId));
    }

    @Override
    public List<ProductMetadataResponse> getProductMetadataById(List<Long> productIds) {
        return productFeignClient.getProductMetadata(productIds);
    }
}
