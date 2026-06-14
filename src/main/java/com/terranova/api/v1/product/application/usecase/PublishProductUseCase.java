package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.location.LocationInfo;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.product.domain.port.out.LocationPort;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;
import com.terranova.api.v1.product.domain.port.out.ValidatorPort;
import com.terranova.api.v1.shared.domain.port.ProductOwnershipValidatorPort;

import java.time.LocalDateTime;
import java.util.List;

public class PublishProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ProductOwnershipValidatorPort productOwnershipValidatorPort;
    private final ImageRepositoryPort imageRepositoryPort;
    private final ValidatorPort validatorPort;
    private final LocationPort locationPort;

    public PublishProductUseCase(ProductRepositoryPort productRepositoryPort, ProductOwnershipValidatorPort productOwnershipValidatorPort, ImageRepositoryPort imageRepositoryPort, ValidatorPort validatorPort, LocationPort locationPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.productOwnershipValidatorPort = productOwnershipValidatorPort;
        this.imageRepositoryPort = imageRepositoryPort;
        this.validatorPort = validatorPort;
        this.locationPort = locationPort;
    }

    public Product publish(Long productId){

        Product product = productOwnershipValidatorPort.validateOwnership(productId);

        LocationInfo locationInfo = locationPort.getLocation(product.getLatitude(), product.getLongitude());
        Product withLocation =
                product.withLocation(
                        locationInfo.city(),
                        locationInfo.department(),
                        locationInfo.country()
                );

        List<Image> images = imageRepositoryPort.getByProductId(List.of(productId)).getOrDefault(productId, List.of());
        Product withImages = withLocation.withImages(images);

        validatorPort.validatePublish(withImages);

        return productRepositoryPort.save(withLocation.publish(LocalDateTime.now()));
    }
}
