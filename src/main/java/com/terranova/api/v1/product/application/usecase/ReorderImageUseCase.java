package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.model.command.image.ReorderImageCommand;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.shared.domain.port.ProductOwnershipValidatorPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReorderImageUseCase {

    private final ProductOwnershipValidatorPort ownershipValidator;
    private final ImageRepositoryPort imageRepositoryPort;

    public ReorderImageUseCase(
            ProductOwnershipValidatorPort ownershipValidator,
            ImageRepositoryPort imageRepositoryPort
    ) {
        this.ownershipValidator = ownershipValidator;
        this.imageRepositoryPort = imageRepositoryPort;
    }

    public void reorderImages(
            Long productId,
            List<ReorderImageCommand> commands
    ){

        ownershipValidator.validateOwnership(productId);

        Set<Integer> orders =
                commands.stream()
                        .map(ReorderImageCommand::displayOrder)
                        .collect(Collectors.toSet());

        if(orders.size() != commands.size()){
            throw new BusinessException(
                    ErrorCodeEnum.INVALID_IMAGE_ORDER,
                    "Display orders must be unique"
            );
        }

        imageRepositoryPort.reorderImages(
                productId,
                commands
        );
    }
}
