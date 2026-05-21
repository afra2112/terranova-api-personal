package com.terranova.api.v1.product.domain.model;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;
import com.terranova.api.v1.product.domain.model.enums.LandAccessEnum;
import com.terranova.api.v1.product.domain.model.enums.LandTopographyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Land extends Product{
    private Double landSizeInM2;
    private String currentUse;
    private LandTopographyEnum topography;
    private LandAccessEnum access;
    private String currentServices;

    @Override
    public Product withImages(List<Image> images) {
        return this.toBuilder()
                .images(List.copyOf(images))
                .build();
    }

    @Override
    public Product withAppointments(List<Appointment> appointments) {
        return this.toBuilder()
                .appointments(List.copyOf(appointments))
                .build();
    }

    @Override
    public Product withSellerSummary(SellerSummary sellerSummary) {
        return this.toBuilder()
                .sellerSummary(sellerSummary)
                .build();
    }
}
