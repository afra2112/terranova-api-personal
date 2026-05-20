package com.terranova.api.v1.product.infrastructure.adapter.out.users;

import com.terranova.api.v1.product.domain.model.SellerSummary;
import com.terranova.api.v1.product.domain.port.out.UserPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UserAdapter implements UserPort {

    private final UserFeign userFeign;

    @Override
    public Map<UUID, SellerSummary> getSellerSummaryBatch(List<UUID> ids) {
        return userFeign.getSellerSummaryBatch(ids);
    }
}
