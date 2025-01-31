package org.gatodev.arcadiaclinica.service.medical;

import org.gatodev.arcadiaclinica.DTO.medical.MedicalServiceDTO;
import org.gatodev.arcadiaclinica.DTO.medical.MedicalServiceWPDTO;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.gatodev.arcadiaclinica.entity.medical.MedicalTypeService;
import java.util.List;

public interface IMedicalServiceService {
    MedicalService addMedicalService(MedicalService medicalService);

    MedicalService updateMedicalService(MedicalService medicalService);

    MedicalService getMedicalServiceById(Long id);

    void deactivateMedicalServiceById(Long id);

    void activateMedicalServiceById(Long id);

    List<MedicalService> getAllMedicalService();

    MedicalServiceDTO convertToDTO(MedicalService medicalService);

    MedicalServiceWPDTO convertToWPDTO(MedicalService medicalService);

    void changeStateByMedicalTypeService(MedicalTypeService medicalTypeService, boolean state);

    void changeStateByMedicalSpecialty(MedicalSpecialty medicalSpecialty, boolean state);
}
