package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.appointment.Attendance;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;
import com.terranova.api.v1.product.domain.port.out.AppointmentPort;
import com.terranova.api.v1.product.domain.port.out.AttendancePort;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;
import com.terranova.api.v1.shared.domain.port.ProductOwnershipValidatorPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;

import java.time.LocalDateTime;

public class SoldProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ProductOwnershipValidatorPort productOwnershipValidatorPort;
    private final AttendancePort attendancePort;
    private final AppointmentPort appointmentPort;

    public SoldProductUseCase(ProductRepositoryPort productRepositoryPort, ProductOwnershipValidatorPort productOwnershipValidatorPort, AttendancePort attendancePort, AppointmentPort appointmentPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.productOwnershipValidatorPort = productOwnershipValidatorPort;
        this.attendancePort = attendancePort;
        this.appointmentPort = appointmentPort;
    }

    public Product soldProduct(Long id, Long attendanceId){

        Product ownershipValidated = productOwnershipValidatorPort.validateOwnership(id);

        if (ownershipValidated.getStatus() != StatusEnum.PUBLISHED){
            throw new BusinessException(ErrorCodeEnum.WRONG_PRODUCT_STATUS, "You can only mark a product as SOLD when the product currently is in PUBLISHED status.");
        }

        Attendance attendance = attendancePort.getAttendanceById(attendanceId);

        Product sold = ownershipValidated.sold(LocalDateTime.now(), attendance.userId(), attendance.appointmentId());

        appointmentPort.

        return productRepositoryPort.save(sold);
    }
}
