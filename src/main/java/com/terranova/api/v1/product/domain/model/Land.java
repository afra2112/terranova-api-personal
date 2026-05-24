package com.terranova.api.v1.product.domain.model;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;
import com.terranova.api.v1.product.domain.model.command.draft.patch.PatchLandCommand;
import com.terranova.api.v1.product.domain.model.command.draft.patch.PatchProductCommand;
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
    public Product patch(PatchProductCommand command) {
        PatchLandCommand cmd = (PatchLandCommand) command;
        return Land.builder()
                .status(this.getStatus())
                .productType(this.getProductType())
                .sellerId(this.getSellerId())
                .productId(this.getProductId())
                .name(cmd.name() != null ? cmd.name() : this.getName())
                .price(cmd.price() != null ? cmd.price() : this.getPrice())
                .description(cmd.description() != null ? cmd.description() : this.getDescription())
                .city(cmd.city() != null ? cmd.city() : this.getCity())
                .latitude(cmd.latitude() != null ? cmd.latitude() : this.getLatitude())
                .longitude(cmd.longitude() != null ? cmd.longitude() : this.getLongitude())
                .landSizeInM2(cmd.landSizeInM2() != null ? cmd.landSizeInM2() : this.landSizeInM2)
                .currentUse(cmd.currentUse() != null ? cmd.currentUse() : this.currentUse)
                .topography(cmd.topography() != null ? cmd.topography() : this.topography)
                .access(cmd.access() != null ? cmd.access() : this.access)
                .currentServices(cmd.currentServices() != null ? cmd.currentServices() : this.currentServices)
                .build();
    }

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
