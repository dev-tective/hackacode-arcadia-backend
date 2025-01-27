package org.gatodev.arcadiaclinica.DTO.medical;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.util.List;

//Servicio medico con paquete
@Getter
@Setter
@SuperBuilder
public class MedicalServiceWPDTO extends MedicalServiceDTO {
    private List<MedicalServicePackageDTO> medicalServicePackages;
}
