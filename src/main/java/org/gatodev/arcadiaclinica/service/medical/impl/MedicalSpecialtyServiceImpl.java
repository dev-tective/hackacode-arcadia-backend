package org.gatodev.arcadiaclinica.service.medical.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.gatodev.arcadiaclinica.repository.medical.IMedicalSpecialtyRepository;
import org.gatodev.arcadiaclinica.service.medical.IMedicalSpecialtyService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicalSpecialtyServiceImpl implements IMedicalSpecialtyService {

    private final IMedicalSpecialtyRepository medicalSpecialtyRepository;

    public MedicalSpecialtyServiceImpl(IMedicalSpecialtyRepository medicalSpecialtyRepository) {
        this.medicalSpecialtyRepository = medicalSpecialtyRepository;
    }

    @Override
    public MedicalSpecialty addMedicalSpecialty(MedicalSpecialty medicalSpecialty) {
        return medicalSpecialtyRepository.save(medicalSpecialty);
    }

    @Override
    public MedicalSpecialty updateMedicalSpecialty(MedicalSpecialty medicalSpecialty) {
        if (medicalSpecialty.getId() == null ||
                !medicalSpecialtyRepository.existsById(medicalSpecialty.getId())) {
            throw new EntityNotFoundException("Medical Specialty Not Found");
        }
        medicalSpecialty.setState(true);
        return medicalSpecialtyRepository.save(medicalSpecialty);
    }

    @Override
    public MedicalSpecialty getMedicalSpecialtyById(long id) {
        return medicalSpecialtyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical Specialty not found"));
    }

    @Override
    public List<MedicalSpecialty> getAllMedicalSpecialties() {
        return medicalSpecialtyRepository.findAll();
    }

    @Override
    public void deactivateMedicalSpecialty(long id) {

    }

    @Override
    public void activateMedicalSpecialty(long id) {

    }
}
