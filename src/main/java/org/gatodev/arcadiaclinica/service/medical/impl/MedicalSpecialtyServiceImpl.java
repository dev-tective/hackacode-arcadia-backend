package org.gatodev.arcadiaclinica.service.medical.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.gatodev.arcadiaclinica.repository.medical_services.IMedicalSpecialtyRepository;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServiceService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalSpecialtyService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("medicalSpecialty")
public class MedicalSpecialtyServiceImpl implements IMedicalSpecialtyService {

    private final IMedicalServiceService medicalServiceService;
    private final IMedicalSpecialtyRepository medicalSpecialtyRepository;

    public MedicalSpecialtyServiceImpl(
            IMedicalServiceService medicalServiceService,
            IMedicalSpecialtyRepository medicalSpecialtyRepository
    ) {
        this.medicalServiceService = medicalServiceService;
        this.medicalSpecialtyRepository = medicalSpecialtyRepository;
    }

    @Override
    public MedicalSpecialty addMedicalSpecialty(MedicalSpecialty category) {
        if (medicalSpecialtyRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("MedicalSpecialty name already exists");
        }
        return medicalSpecialtyRepository.save(category);
    }

    @Override
    public MedicalSpecialty updateMedicalSpecialty(MedicalSpecialty category) {
        if (!medicalSpecialtyRepository.existsById(category.getId())) {
            throw new RuntimeException("MedicalSpecialty does not exist");
        }
        category.setState(true);
        return medicalSpecialtyRepository.save(category);
    }

    @Override
    public MedicalSpecialty getMedicalSpecialtyById(Long id) {
        return medicalSpecialtyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical Specialty Not Found"));
    }

    @Transactional
    @Override
    public void deactivateMedicalSpecialtyById(Long id) {
        MedicalSpecialty ms = getMedicalSpecialtyById(id);
        ms.setState(false);
        medicalServiceService.changeStateByMedicalSpecialty(ms, false);
        medicalSpecialtyRepository.save(ms);
    }

    @Transactional
    @Override
    public void activateMedicalSpecialtyById(Long id) {
        MedicalSpecialty ms = getMedicalSpecialtyById(id);
        ms.setState(true);
        medicalServiceService.changeStateByMedicalSpecialty(ms, true);
        medicalSpecialtyRepository.save(ms);
    }

    @Override
    public List<MedicalSpecialty> getAllMedicalSpecialty() {
        return medicalSpecialtyRepository.findAll();
    }
}
