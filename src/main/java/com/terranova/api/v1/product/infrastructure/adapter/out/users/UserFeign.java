package com.terranova.api.v1.product.infrastructure.adapter.out.users;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user", url = "http://localhost:8080/api/v1/users")
public interface UserFeign {
}
