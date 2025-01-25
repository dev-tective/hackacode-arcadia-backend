package org.gatodev.arcadiaclinica.service.medical_services;

import org.gatodev.arcadiaclinica.entity.medical_services.MedicalTypeService;
import java.util.List;

public interface IMedicalTypeServiceService {
    MedicalTypeService addMedicalTypeService(MedicalTypeService typeService);

    MedicalTypeService updateMedicalTypeService(MedicalTypeService typeService);

    MedicalTypeService getMedicalTypeServiceById(int id);

    void desactivateMedicalTypeServiceById(int id);

    void activateMedicalTypeServiceById(int id);

    void existsMedicalTypeServiceById(int id);

    List<MedicalTypeService> getAllMedicalTypeService();
}
