package org.gatodev.arcadiaclinica.service.medical_services.impl;

import org.gatodev.arcadiaclinica.entity.medical_services.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical_services.MedicalSpecialty;
import org.gatodev.arcadiaclinica.repository.medical_services.IMedicalSpecialtyRepository;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalServiceService;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalSpecialtyService;
import org.springframework.stereotype.Service;
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

    @Override
    public MedicalSpecialty updateMedicalSpecialty(MedicalSpecialty ms) {
        existMedicalSpecialtyById(ms.getId());
        ms.validateCode();
        ms.setState(true);
        return medicalSpecialtyRepository.save(ms);
    }

    @Override
    public MedicalSpecialty getMedicalSpecialtyById(int id) {
        return medicalSpecialtyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical Specialty not found"));
    }

    @Override
    public void desactivateMedicalSpecialtyById(int id) {
        MedicalSpecialty ms = getMedicalSpecialtyById(id);
        ms.setState(false);
        medicalServiceService.changeStateByMedicalSpecialty(ms, false);
    }

    @Override
    public void activateMedicalSpecialtyById(int id) {
        MedicalSpecialty ms = getMedicalSpecialtyById(id);
        ms.setState(true);
        medicalServiceService.changeStateByMedicalSpecialty(ms, true);
    }

    @Override
    public void existMedicalSpecialtyById(int id) {
        if (!medicalSpecialtyRepository.existsById(id)) {
            throw new RuntimeException("Medical Specialty Not Found");
        }
    }

    @Override
    public List<MedicalSpecialty> getAllMedicalSpecialty() {
        return medicalSpecialtyRepository.findAll();
    }
}
