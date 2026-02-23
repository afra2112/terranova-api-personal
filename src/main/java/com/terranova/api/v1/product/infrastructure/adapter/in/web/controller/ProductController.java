package com.terranova.api.v1.product.infrastructure.adapter.in.web.controller;

import com.terranova.api.v1.product.application.usecase.CreateProductUseCase;
import com.terranova.api.v1.product.domain.model.group.CattleGroup;
import com.terranova.api.v1.product.domain.model.group.FarmGroup;
import com.terranova.api.v1.product.domain.model.group.LandGroup;
import com.terranova.api.v1.product.domain.port.out.ValidatorPort;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.CreateProductRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.CreateProductResponse;
import com.terranova.api.v1.product.infrastructure.adapter.mapper.ProductMapper;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ProductMapper productMapper;
    private final ValidatorPort validatorPort;

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request){
        validatorPort.validate(request, getGroupFromRequestProductType(request.productType().name()));
        return ResponseEntity.ok().body(productMapper.domainToResponse(createProductUseCase.createProduct(productMapper.requestToCommand(request))));
    }

    private Class<?> getGroupFromRequestProductType(String productType){
        return switch (productType){
            case "FARM" -> FarmGroup.class;
            case "LAND" -> LandGroup.class;
            case "CATTLE" -> CattleGroup.class;
            default -> throw new BusinessException(ErrorCodeEnum.PRODUCT_TYPE_NOT_SUPPORTED);
        };
    }
}