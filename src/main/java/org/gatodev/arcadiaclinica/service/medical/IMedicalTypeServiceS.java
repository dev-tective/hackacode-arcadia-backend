package org.gatodev.arcadiaclinica.service.medical;

import org.gatodev.arcadiaclinica.entity.medical.MedicalTypeService;
import java.util.List;

public interface IMedicalTypeServiceS {
    MedicalTypeService addMedicalTypeService(MedicalTypeService medicalTypeService);

    MedicalTypeService updateMedicalTypeService(MedicalTypeService medicalTypeService);

    MedicalTypeService getMedicalTypeServiceById(long id);

    List<MedicalTypeService> getAllMedicalTypeServices();

    void deactivateMedicalTypeService(long id);

    void activateMedicalTypeService(long id);
}
