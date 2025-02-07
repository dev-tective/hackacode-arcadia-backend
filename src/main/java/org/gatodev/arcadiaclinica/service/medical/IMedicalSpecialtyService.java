package org.gatodev.arcadiaclinica.service.medical;

import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import java.util.List;

public interface IMedicalSpecialtyService {
    MedicalSpecialty addMedicalSpecialty(MedicalSpecialty medicalSpecialty);

    MedicalSpecialty updateMedicalSpecialty(MedicalSpecialty medicalSpecialty);

    MedicalSpecialty getMedicalSpecialtyById(long id);

    List<MedicalSpecialty> getAllMedicalSpecialties();

    void deactivateMedicalSpecialty(long id);

    void activateMedicalSpecialty(long id);
}
