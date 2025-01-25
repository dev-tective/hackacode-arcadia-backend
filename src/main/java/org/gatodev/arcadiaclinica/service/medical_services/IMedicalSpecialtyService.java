package org.gatodev.arcadiaclinica.service.medical_services;

import org.gatodev.arcadiaclinica.entity.medical_services.MedicalSpecialty;
import java.util.List;

public interface IMedicalSpecialtyService {
    MedicalSpecialty addMedicalSpecialty(MedicalSpecialty ms);

    MedicalSpecialty updateMedicalSpecialty(MedicalSpecialty ms);

    MedicalSpecialty getMedicalSpecialtyById(int id);

    void desactivateMedicalSpecialtyById(int id);

    void activateMedicalSpecialtyById(int id);

    void existMedicalSpecialtyById(int id);

    List<MedicalSpecialty> getAllMedicalSpecialty();
}
