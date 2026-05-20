package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.domain.model.SellerSummary;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserPort {

    Map<UUID, SellerSummary> getSellerSummaryBatch(List<UUID> ids);
}
