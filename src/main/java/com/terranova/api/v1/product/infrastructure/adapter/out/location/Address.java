package com.terranova.api.v1.product.infrastructure.adapter.out.location;

public record Address(
        String city,
        String town,
        String village,
        String state,
        String country
) {
}
