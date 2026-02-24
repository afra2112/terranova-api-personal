package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.jpa;

import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaImageRepository extends JpaRepository<ImageEntity, Long> {
}
