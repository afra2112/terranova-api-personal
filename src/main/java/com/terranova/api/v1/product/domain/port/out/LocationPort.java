package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.domain.model.location.LocationInfo;

public interface LocationPort {

    LocationInfo getLocation(
            Double latitude,
            Double longitude
    );
}
