package org.gatodev.arcadiaclinica.DTO.medical;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
public class MedicalServicePackageDTO extends MedicalServiceDTO {
    private LocalDate available;
    private LocalDate notAvailable;
    private List<MedicalServiceDTO> medicalServices;
}
