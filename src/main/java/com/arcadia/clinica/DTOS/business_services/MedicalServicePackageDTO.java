package com.arcadia.clinica.DTOS.business_services;

import com.arcadia.clinica.entity.business_services.MedicalService;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalServicePackageDTO {

    @NotBlank(message = "code must not be blank")
    @Size(min = 10, max = 20, message = "code must be between 10 and 20 characters")
    private String code;
    @NotNull(message = "price not null")
    @Min(value = 1, message = "Price must be at least 1")
    @Max(value = 30000, message = "price must not exceed 30,000")
    private BigDecimal price;
    @FutureOrPresent(message = "available date must be today or in the future")
    private LocalDate available;
    @FutureOrPresent(message = "notAvailable date must be today or in the future")
    private LocalDate notAvailable;
    @NotNull(message = "state must not be null")
    private Boolean status;
    @NotEmpty(message = "medicalServices list must not be empty")
    private List<MedicalService> medicalServices;


}
