package org.gatodev.arcadiaclinica.service.medical;

import org.gatodev.arcadiaclinica.entity.medical.MedicalTypeService;
import java.util.List;

public interface IMedicalTypeServiceService {
    MedicalTypeService addMedicalTypeService(MedicalTypeService category);

    MedicalTypeService updateMedicalTypeService(MedicalTypeService category);

    MedicalTypeService getMedicalTypeServiceById(Long id);

    void deactivateMedicalTypeServiceById(Long id);

    void activateMedicalTypeServiceById(Long id);

    List<MedicalTypeService> getAllMedicalTypeService();
}
