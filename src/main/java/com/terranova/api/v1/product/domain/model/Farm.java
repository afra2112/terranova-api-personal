package com.terranova.api.v1.product.domain.model;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;
import com.terranova.api.v1.product.domain.model.command.draft.patch.PatchFarmCommand;
import com.terranova.api.v1.product.domain.model.command.draft.patch.PatchProductCommand;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Farm extends Product {
    private Double totalSpaceInM2;
    private Double builtSpaceInM2;
    private Integer stratum;
    private Integer roomsQuantity;
    private Integer bathroomsQuantity;

    @Override
    public Product patch(PatchProductCommand command) {
        PatchFarmCommand cmd = (PatchFarmCommand) command;
        return Farm.builder()
                .status(this.getStatus())
                .productType(this.getProductType())
                .sellerId(this.getSellerId())
                .productId(this.getProductId())
                .name(cmd.name() != null ? cmd.name() : this.getName())
                .price(cmd.price() != null ? cmd.price() : this.getPrice())
                .description(
                        cmd.description() != null
                                ? cmd.description()
                                : this.getDescription()
                )
                .city(cmd.city() != null ? cmd.city() : this.getCity())
                .latitude(cmd.latitude() != null ? cmd.latitude() : this.getLatitude())
                .longitude(cmd.longitude() != null ? cmd.longitude() : this.getLongitude())
                .totalSpaceInM2(cmd.totalSpaceInM2() != null ? cmd.totalSpaceInM2() : this.totalSpaceInM2)
                .builtSpaceInM2(cmd.builtSpaceInM2() != null ? cmd.builtSpaceInM2() : this.builtSpaceInM2)
                .stratum(cmd.stratum() != null ? cmd.stratum() : this.stratum)
                .roomsQuantity(cmd.roomsQuantity() != null ? cmd.roomsQuantity() : this.roomsQuantity)
                .bathroomsQuantity(cmd.bathroomsQuantity() != null ? cmd.bathroomsQuantity() : this.bathroomsQuantity)
                .build();
    }

    @Override
    public Product publish(LocalDateTime publishDate) {
        return this.toBuilder()
                .publishDate(publishDate)
                .status(StatusEnum.PUBLISHED)
                .build();
    }

    @Override
    public Product sold(LocalDateTime soldDate, UUID soldToUserId, Long soldFromAppointmentId) {
        return this.toBuilder()
                .soldDate(soldDate)
                .status(StatusEnum.SOLD)
                .soldToUserId(soldToUserId)
                .soldFromAppointmentId(soldFromAppointmentId)
                .build();
    }

    @Override
    public Product withLocation(
            String city,
            String department,
            String country
    ) {
        return this.toBuilder()
                .city(city)
                .department(department)
                .country(country)
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
