package com.arcadia.clinica.DTOS.business_services;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class MedicalServiceDTO {

    @NotBlank(message = "code must not be blank")
    @Size(min = 10, max = 20, message = "code must be between 10 and 20 characters")
    private String code;
    @NotNull(message = "price not null")
    @Min(value = 1, message = "Price must be at least 1")
    @Max(value = 30000, message = "price must not exceed 30,000")
    private BigDecimal price;
    @NotBlank(message = "name must not be blank")
    @Size(min = 5, max = 15, message = "name must be between 5 and 15 characters")
    private String name;
    @NotBlank(message = "description must not be blank")
    @Size(min = 5, max = 15, message = "description must be between 5 and 15 characters")
    private String description;
    @NotNull(message = "duration must not be null")
    private Duration duration;
    @NotBlank(message = "description must not be blank")
    @Size(min = 5, max = 15, message = "speciality name must be between 5 and 15 characters")
    private String specialityName;
    @NotBlank(message = "description must not be blank")
    @Size(min = 5, max = 15, message = "type service name must be between 5 and 15 characters")
    private String typeServiceName;

}
