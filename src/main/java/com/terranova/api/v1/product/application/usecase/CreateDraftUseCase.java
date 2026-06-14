package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.factory.ProductFactory;
import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.command.draft.CreateDraftCommand;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;
import com.terranova.api.v1.shared.security.utils.AuthFacade;

public class CreateDraftUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ProductFactory productFactory;
    private final AuthFacade authFacade;

    public CreateDraftUseCase(ProductRepositoryPort productRepositoryPort, ProductFactory productFactory, AuthFacade authFacade) {
        this.productRepositoryPort = productRepositoryPort;
        this.productFactory = productFactory;
        this.authFacade = authFacade;
    }

    public CreateDraftCommand createDraft(CreateDraftCommand command){
        Product product = productRepositoryPort.save(productFactory.createDraft(command.productType(), authFacade.getAuthenticatedId()));
        return new CreateDraftCommand(
                product.getProductId(),
                product.getStatus(),
                product.getProductType(),
                product.getSellerId()
        );
    }
}
