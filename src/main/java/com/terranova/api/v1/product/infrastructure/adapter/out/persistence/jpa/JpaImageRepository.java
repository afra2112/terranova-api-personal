package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.jpa;

import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JpaImageRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findByProduct_ProductId(Long productId);
}
