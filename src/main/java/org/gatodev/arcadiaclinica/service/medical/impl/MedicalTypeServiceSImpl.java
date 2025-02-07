package org.gatodev.arcadiaclinica.service.medical.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.entity.medical.MedicalTypeService;
import org.gatodev.arcadiaclinica.repository.medical.IMedicalTypeServiceRepository;
import org.gatodev.arcadiaclinica.service.medical.IMedicalTypeServiceS;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicalTypeServiceSImpl implements IMedicalTypeServiceS {

    private final IMedicalTypeServiceRepository medicalTypeServiceRepository;

    public MedicalTypeServiceSImpl(IMedicalTypeServiceRepository medicalTypeServiceRepository) {
        this.medicalTypeServiceRepository = medicalTypeServiceRepository;
    }

    @Override
    public MedicalTypeService addMedicalTypeService(MedicalTypeService medicalTypeService) {
        return medicalTypeServiceRepository.save(medicalTypeService);
    }

    @Override
    public MedicalTypeService updateMedicalTypeService(MedicalTypeService medicalTypeService) {
        if (medicalTypeService.getId() == null ||
                !medicalTypeServiceRepository.existsById(medicalTypeService.getId())) {
            throw new EntityNotFoundException("Medical type not found");
        }
        medicalTypeService.setState(true);
        return medicalTypeServiceRepository.save(medicalTypeService);
    }

    @Override
    public MedicalTypeService getMedicalTypeServiceById(long id) {
        return medicalTypeServiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical type service not found"));
    }

    @Override
    public List<MedicalTypeService> getAllMedicalTypeServices() {
        return medicalTypeServiceRepository.findAll();
    }

    @Override
    public void deactivateMedicalTypeService(long id) {

    }

    @Override
    public void activateMedicalTypeService(long id) {

    }
}
