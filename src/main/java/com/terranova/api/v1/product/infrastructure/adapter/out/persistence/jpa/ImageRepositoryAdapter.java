package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.jpa;

import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.domain.model.command.CreateImageCommand;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.product.infrastructure.adapter.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageRepositoryAdapter implements ImageRepositoryPort {

    private final JpaImageRepository jpaImageRepository;
    private final ImageMapper imageMapper;

    @Override
    public List<Image> save(List<CreateImageCommand> images) {
        List<Image> imagesDomain = images.stream().map(image -> imageMapper.entityToDomain(jpaImageRepository.save(imageMapper.domainToEntity(image)))).toList();
        return
    }
}
