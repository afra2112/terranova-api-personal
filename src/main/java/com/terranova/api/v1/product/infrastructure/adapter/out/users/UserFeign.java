package com.terranova.api.v1.product.infrastructure.adapter.out.users;

import com.terranova.api.v1.product.domain.model.SellerSummary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@FeignClient(name = "user", url = "http://localhost:8080/api/v1/users")
public interface UserFeign {

    @PostMapping("/internal/batch")
    Map<UUID, SellerSummary> getSellerSummaryBatch(@RequestBody List<UUID> ids);
}
