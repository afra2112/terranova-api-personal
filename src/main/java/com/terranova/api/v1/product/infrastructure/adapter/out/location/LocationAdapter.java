package com.terranova.api.v1.product.infrastructure.adapter.out.location;

import com.terranova.api.v1.product.domain.port.out.LocationPort;
import com.terranova.api.v1.product.domain.model.location.LocationInfo;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class LocationAdapter implements LocationPort {

    private final RestClient restClient;

    @Override
    public LocationInfo getLocation(
            Double latitude,
            Double longitude
    ) {

        NominatiResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("nominatim.openstreetmap.org")
                        .path("/reverse")
                        .queryParam("lat", latitude)
                        .queryParam("lon", longitude)
                        .queryParam("format", "json")
                        .build())
                .header(
                        "User-Agent",
                        "Terranova/1.0"
                )
                .retrieve()
                .body(NominatiResponse.class);

        if(response == null || response.address() == null){
            throw new BusinessException(
                    ErrorCodeEnum.LOCATION_NOT_FOUND, "Location not found by lat: " + latitude + " and lon: " + longitude
            );
        }

        return mapToLocationInfo(response);
    }

    private LocationInfo mapToLocationInfo(
            NominatiResponse response
    ) {

        Address address = response.address();

        return new LocationInfo(
                address.country(),
                address.state(),
                resolveCity(address)
        );
    }

    private String resolveCity(Address address){

        if(address.city() != null){
            return address.city();
        }

        if(address.town() != null){
            return address.town();
        }

        if(address.village() != null){
            return address.village();
        }

        throw new BusinessException(
                ErrorCodeEnum.LOCATION_NOT_FOUND
        );
    }
}