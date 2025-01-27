package org.gatodev.arcadiaclinica.DTO.medical;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gatodev.arcadiaclinica.DTO.BaseDTO;
import java.math.BigDecimal;
import java.time.Duration;

@Getter
@Setter
@SuperBuilder
public class MedicalServiceDTO extends BaseDTO {
    private String description;
    private Duration duration;
    private BigDecimal price;
    private BaseDTO medicalTypeService;
    private BaseDTO medicalSpecialty;
}
