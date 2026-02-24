package com.terranova.api.v1.product.infrastructure.adapter.in.web.controller;

import com.terranova.api.v1.product.application.usecase.CreateImageUseCase;
import com.terranova.api.v1.product.application.usecase.CreateProductUseCase;
import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.domain.model.command.CreateImageCommand;
import com.terranova.api.v1.product.domain.model.group.CattleGroup;
import com.terranova.api.v1.product.domain.model.group.FarmGroup;
import com.terranova.api.v1.product.domain.model.group.LandGroup;
import com.terranova.api.v1.product.domain.port.out.ValidatorPort;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.CreateProductRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.ImageRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.CreateProductResponse;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.ImageResponse;
import com.terranova.api.v1.product.infrastructure.adapter.mapper.ImageMapper;
import com.terranova.api.v1.product.infrastructure.adapter.mapper.ProductMapper;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final CreateImageUseCase createImageUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final ProductMapper productMapper;
    private final ImageMapper imageMapper;
    private final ValidatorPort validatorPort;

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request){
        validatorPort.validate(request, getGroupFromRequestProductType(request.productType().name()));
        return ResponseEntity.ok().body(productMapper.domainToResponse(createProductUseCase.createProduct(productMapper.requestToCommand(request))));
    }

    @PostMapping(value = "/{productId}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<List<ImageResponse>> saveImagesForProduct(@Valid @NotNull @Positive @PathVariable Long productId, @Valid @RequestBody List<ImageRequest> images){
        List<CreateImageCommand> commands = images.stream()
                .map(req -> {
                    try {
                        return new CreateImageCommand(
                                req.file().getOriginalFilename(),
                                req.file().getContentType(),
                                req.file().getSize(),
                                req.displayOrder(),
                                req.file().getBytes()
                        );
                    } catch (IOException e) {
                        throw new BusinessException(ErrorCodeEnum.IMAGE_READ_ERROR, e.getMessage());
                    }
                })
                .toList();

        return ResponseEntity.ok(createImageUseCase.createImages(commands, productId).stream()
                .map(imageMapper::domainToResponse).toList());
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