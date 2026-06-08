package com.terranova.api.v1.product.domain.model;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;
import com.terranova.api.v1.product.domain.model.command.draft.patch.PatchCattleCommand;
import com.terranova.api.v1.product.domain.model.command.draft.patch.PatchProductCommand;
import com.terranova.api.v1.product.domain.model.enums.CattleGenderEnum;
import com.terranova.api.v1.product.domain.model.enums.CattleTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cattle extends Product{
    private String race;
    private Double weightInKg;
    private Double cattleAgeInYears;
    private CattleGenderEnum gender;
    private CattleTypeEnum cattleType;
    private Integer quantity;

    @Override
    public Product patch(PatchProductCommand command) {
        PatchCattleCommand cmd = (PatchCattleCommand) command;
        return Cattle.builder()
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
                .race(cmd.race() != null ? cmd.race() : this.getRace())
                .weightInKg(
                        cmd.weightInKg() != null
                                ? cmd.weightInKg()
                                : this.getWeightInKg()
                )
                .cattleAgeInYears(
                        cmd.cattleAgeInYears() != null
                                ? cmd.cattleAgeInYears()
                                : this.cattleAgeInYears
                )
                .gender(
                        cmd.gender() != null
                                ? cmd.gender()
                                : this.gender
                )
                .cattleType(
                        cmd.cattleType() != null
                                ? cmd.cattleType()
                                : this.cattleType
                )
                .quantity(
                        cmd.quantity() != null
                                ? cmd.quantity()
                                : this.quantity
                )
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
