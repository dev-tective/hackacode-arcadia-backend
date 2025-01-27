package org.gatodev.arcadiaclinica.service.medical_services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.entity.medical.MedicalTypeService;
import org.gatodev.arcadiaclinica.repository.medical_services.IMedicalTypeServiceRepository;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalServiceService;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalTypeServiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicalTypeServiceServiceImpl implements IMedicalTypeServiceService {

    private final IMedicalTypeServiceRepository medicalTypeServiceRepository;
    private final IMedicalServiceService medicalServiceService;

    public MedicalTypeServiceServiceImpl(
            IMedicalTypeServiceRepository medicalTypeServiceRepository,
            IMedicalServiceService medicalServiceService
    ) {
        this.medicalTypeServiceRepository = medicalTypeServiceRepository;
        this.medicalServiceService = medicalServiceService;
    }

    @Override
    public MedicalTypeService addMedicalTypeService(MedicalTypeService typeService) {
        typeService.validateCode();
        return medicalTypeServiceRepository.save(typeService);
    }

    @Transactional
    @Override
    public MedicalTypeService updateMedicalTypeService(MedicalTypeService typeService) {
        existsMedicalTypeServiceById(typeService.getId());
        typeService.validateCode();
        typeService.setState(true);
        return medicalTypeServiceRepository.save(typeService);
    }

    @Transactional(readOnly = true)
    @Override
    public MedicalTypeService getMedicalTypeServiceById(Long id) {
        return medicalTypeServiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical Type Service with ID "
                        + id + " not found"));
    }

    @Transactional
    @Override
    public void deactivateMedicalTypeServiceById(Long id) {
        MedicalTypeService typeService = getMedicalTypeServiceById(id);
        typeService.setState(false);
        medicalServiceService.changeStateByMedicalTypeService(typeService, false);
    }

    @Transactional
    @Override
    public void activateMedicalTypeServiceById(Long id) {
        MedicalTypeService typeService = getMedicalTypeServiceById(id);
        typeService.setState(true);
        medicalServiceService.changeStateByMedicalTypeService(typeService, true);
    }

    @Override
    public void existsMedicalTypeServiceById(Long id) {
        if (!medicalTypeServiceRepository.existsById(id)) {
            throw new EntityNotFoundException("Medical Type Service Not Found");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<MedicalTypeService> getAllMedicalTypeService() {
        return medicalTypeServiceRepository.findAll();
    }
}
