package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.domain.model.Image;

import java.util.List;

public interface ImageRepositoryPort {

    List<Image> save(List<Image> images, Long productId);


}
