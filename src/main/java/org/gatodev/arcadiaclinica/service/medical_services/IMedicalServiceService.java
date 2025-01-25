package org.gatodev.arcadiaclinica.service.medical_services;

import org.gatodev.arcadiaclinica.entity.medical_services.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical_services.MedicalSpecialty;
import org.gatodev.arcadiaclinica.entity.medical_services.MedicalTypeService;
import java.util.List;

public interface IMedicalServiceService {
    MedicalService addMedicalService(MedicalService medicalService);

    MedicalService updateMedicalService(MedicalService medicalService);

    MedicalService getMedicalServiceById(int id);

    void desactivateMedicalServiceById(int id);

    void activateMedicalServiceById(int id);

    List<MedicalService> getAllMedicalService();

    void changeStateByMedicalTypeService(MedicalTypeService medicalTypeService, boolean state);

    void changeStateByMedicalSpecialty(MedicalSpecialty medicalSpecialty, boolean state);
}
