package com.terranova.api.v1.product.domain.model;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;
import com.terranova.api.v1.product.domain.model.command.draft.patch.PatchProductCommand;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
public abstract class Product {
    private Long productId;
    private String name;
    private BigDecimal price;
    private String description;
    private StatusEnum status;
    private LocalDateTime publishDate;
    private LocalDateTime soldDate;
    private UUID soldToUserId;
    private Long soldFromAppointmentId;
    private String city;
    private String department;
    private String country;
    private Double latitude;
    private Double longitude;
    private UUID sellerId;
    private ProductTypeEnum productType;
    private List<Image> images;
    private List<Appointment> appointments;
    private SellerSummary sellerSummary;

    public abstract Product patch(PatchProductCommand command);
    public abstract Product publish(LocalDateTime publishDate);
    public abstract Product sold(LocalDateTime soldDate, UUID soldToUserId, Long soldFromAppointmentId);
    public abstract Product withLocation(String city, String department, String country);
    public abstract Product withImages(List<Image> images);
    public abstract Product withAppointments(List<Appointment> appointments);
    public abstract Product withSellerSummary(SellerSummary sellerSummary);
}
