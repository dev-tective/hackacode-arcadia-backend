package org.gatodev.arcadiaclinica.service.medical_services;

import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import java.util.List;

public interface IMedicalSpecialtyService {
    MedicalSpecialty addMedicalSpecialty(MedicalSpecialty ms);

    MedicalSpecialty updateMedicalSpecialty(MedicalSpecialty ms);

    MedicalSpecialty getMedicalSpecialtyById(Long id);

    void deactivateMedicalSpecialtyById(Long id);

    void activateMedicalSpecialtyById(Long id);

    void existMedicalSpecialtyById(Long id);

    List<MedicalSpecialty> getAllMedicalSpecialty();
}
