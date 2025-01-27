package org.gatodev.arcadiaclinica.service.medical_services.impl;

import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.gatodev.arcadiaclinica.repository.medical_services.IMedicalSpecialtyRepository;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalServiceService;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalSpecialtyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
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
    public MedicalSpecialty addMedicalSpecialty(MedicalSpecialty ms) {
        ms.validateCode();
        return medicalSpecialtyRepository.save(ms);
    }

    @Transactional
    @Override
    public MedicalSpecialty updateMedicalSpecialty(MedicalSpecialty ms) {
        existMedicalSpecialtyById(ms.getId());
        ms.validateCode();
        ms.setState(true);
        return medicalSpecialtyRepository.save(ms);
    }

    @Transactional(readOnly = true)
    @Override
    public MedicalSpecialty getMedicalSpecialtyById(Long id) {
        return medicalSpecialtyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical Specialty not found"));
    }

    @Transactional
    @Override
    public void deactivateMedicalSpecialtyById(Long id) {
        MedicalSpecialty ms = getMedicalSpecialtyById(id);
        ms.setState(false);
        medicalSpecialtyRepository.save(ms);
        medicalServiceService.changeStateByMedicalSpecialty(ms, false);
    }

    @Transactional
    @Override
    public void activateMedicalSpecialtyById(Long id) {
        MedicalSpecialty ms = getMedicalSpecialtyById(id);
        ms.setState(true);
        medicalSpecialtyRepository.save(ms);
        medicalServiceService.changeStateByMedicalSpecialty(ms, true);
    }

    @Transactional(readOnly = true)
    @Override
    public void existMedicalSpecialtyById(Long id) {
        if (!medicalSpecialtyRepository.existsById(id)) {
            throw new RuntimeException("Medical Specialty Not Found");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<MedicalSpecialty> getAllMedicalSpecialty() {
        return medicalSpecialtyRepository.findAll();
    }
}
