package org.gatodev.arcadiaclinica.service.medical;

import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import java.util.List;

public interface IMedicalServiceS {
    MedicalService addMedicalService(MedicalService medicalService);

    MedicalService updateMedicalService(MedicalService medicalService);

    MedicalService getMedicalServiceById(long id);

    List<MedicalService> getAllMedicalServices();

    void deactivateMedicalService(long id);

    void activateMedicalService(long id);
}
