package org.gatodev.arcadiaclinica.service.medical;

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

    void existMedicalService(String name, String code);

    List<MedicalService> getAllMedicalService();

    void changeStateByMedicalTypeService(MedicalTypeService mts, boolean state);

    void changeStateByMedicalSpecialty(MedicalSpecialty ms, boolean state);
}
