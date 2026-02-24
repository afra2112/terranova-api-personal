package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "images")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImagen;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private long size;

    @Column(nullable = false)
    private Integer displayOrder;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private boolean isExternallySaved = false;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity product;
}