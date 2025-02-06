package org.gatodev.arcadiaclinica.service.medical;

import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import java.util.List;

public interface IMedicalSpecialtyService {
    MedicalSpecialty addMedicalSpecialty(MedicalSpecialty category);

    MedicalSpecialty updateMedicalSpecialty(MedicalSpecialty category);

    MedicalSpecialty getMedicalSpecialtyById(Long id);

    void deactivateMedicalSpecialtyById(Long id);

    void activateMedicalSpecialtyById(Long id);

    List<MedicalSpecialty> getAllMedicalSpecialty();
}
