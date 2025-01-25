package org.gatodev.arcadiaclinica.service.medical_services.impl;

import org.gatodev.arcadiaclinica.entity.medical_services.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical_services.MedicalTypeService;
import org.gatodev.arcadiaclinica.repository.medical_services.IMedicalTypeServiceRepository;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalServiceService;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalTypeServiceService;
import org.springframework.stereotype.Service;
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

    @Override
    public MedicalTypeService updateMedicalTypeService(MedicalTypeService typeService) {
        existsMedicalTypeServiceById(typeService.getId());
        typeService.validateCode();
        typeService.setState(true);
        return medicalTypeServiceRepository.save(typeService);
    }

    @Override
    public MedicalTypeService getMedicalTypeServiceById(int id) {
        return medicalTypeServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found"));
    }

    @Override
    public void desactivateMedicalTypeServiceById(int id) {
        MedicalTypeService typeService = getMedicalTypeServiceById(id);
        typeService.setState(false);
        medicalServiceService.changeStateByMedicalTypeService(typeService, false);
    }

    @Override
    public void activateMedicalTypeServiceById(int id) {
        MedicalTypeService typeService = getMedicalTypeServiceById(id);
        typeService.setState(true);
        medicalServiceService.changeStateByMedicalTypeService(typeService, true);
    }

    @Override
    public void existsMedicalTypeServiceById(int id) {
        if (!medicalTypeServiceRepository.existsById(id)) {
            throw new RuntimeException("Medical Type Service Not Found");
        }
    }

    @Override
    public List<MedicalTypeService> getAllMedicalTypeService() {
        return medicalTypeServiceRepository.findAll();
    }
}
