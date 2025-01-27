package org.gatodev.arcadiaclinica.service.medical_services;

import org.gatodev.arcadiaclinica.entity.medical.MedicalTypeService;
import java.util.List;

public interface IMedicalTypeServiceService {
    MedicalTypeService addMedicalTypeService(MedicalTypeService typeService);

    MedicalTypeService updateMedicalTypeService(MedicalTypeService typeService);

    MedicalTypeService getMedicalTypeServiceById(Long id);

    void deactivateMedicalTypeServiceById(Long id);

    void activateMedicalTypeServiceById(Long id);

    void existsMedicalTypeServiceById(Long id);

    List<MedicalTypeService> getAllMedicalTypeService();
}
