package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.domain.model.command.image.ReorderImageCommand;

import java.util.List;
import java.util.Map;

public interface ImageRepositoryPort {

    List<Image> save(List<Image> images, Long productId);

    List<Image> getByProductId(Long productId);

    void reorderImages(Long productId, List<ReorderImageCommand> commands);

    void setCoverImage(Long productId, Long imageId);

    List<Image> getByProductIdAndIdIn(Long ProductId, List<Long> ids);

    Map<Long, List<Image>> getByProductId(List<Long> productsIds);

    Integer getMaxDisplayOrder(Long productId);

    boolean existsCoverImage(Long productId);

    int countByProductId(Long productId);

    int deleteByProductIdAndIds(Long productId, List<Long> imageIds);
}
