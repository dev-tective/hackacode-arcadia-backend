package org.gatodev.arcadiaclinica.DTO.medical;

import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalServiceMapper {

    // Mapea el objeto principal MedicalService a MedicalServiceWPDTO
    MedicalServiceWPDTO toMedicalServiceWPDTO(MedicalService medicalService);

    // Mapea una lista de objetos MedicalService a una lista de MedicalServiceDTO
    @IterableMapping(elementTargetType = MedicalServiceDTO.class)
    List<MedicalServiceDTO> toMedicalServiceDTOList(List<MedicalService> medicalServices);

    // Mapea una lista de objetos MedicalServicePackage a una lista de MedicalServicePackageDTO
    @IterableMapping(elementTargetType = MedicalServicePackageDTO.class)
    List<MedicalServicePackageDTO> toMedicalServicePackageDTOList(List<MedicalServicePackage> medicalServicePackages);

    // Método para mapear MedicalServicePackage a MedicalServicePackageDTO
    MedicalServicePackageDTO toMedicalServicePackageDTO(MedicalServicePackage medicalServicePackage);

    // Mapea MedicalService a MedicalServiceDTO
    MedicalServiceDTO toMedicalServiceDTO(MedicalService medicalService);
}
