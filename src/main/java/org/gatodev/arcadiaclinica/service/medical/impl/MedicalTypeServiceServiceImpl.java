package org.gatodev.arcadiaclinica.service.medical.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.entity.medical.MedicalTypeService;
import org.gatodev.arcadiaclinica.repository.medical_services.IMedicalTypeServiceRepository;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServiceService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalTypeServiceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Qualifier("medicalTypeService")
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
    public MedicalTypeService addMedicalTypeService(MedicalTypeService category) {
        if (medicalTypeServiceRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("MedicalTypeService name already exists");
        }
        return medicalTypeServiceRepository.save(category);
    }

    @Override
    public MedicalTypeService updateMedicalTypeService(MedicalTypeService category) {
        if (!medicalTypeServiceRepository.existsById(category.getId())) {
            throw new EntityNotFoundException("MedicalTypeService id does not exist");
        }
        category.setState(true);
        return medicalTypeServiceRepository.save(category);
    }

    @Override
    public MedicalTypeService getMedicalTypeServiceById(Long id) {
        return medicalTypeServiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical type not found"));
    }

    @Transactional
    @Override
    public void deactivateMedicalTypeServiceById(Long id) {
        MedicalTypeService mts = getMedicalTypeServiceById(id);
        mts.setState(false);
        medicalServiceService.changeStateByMedicalTypeService(mts,false);
        medicalTypeServiceRepository.save(mts);
    }

    @Override
    public void activateMedicalTypeServiceById(Long id) {
        MedicalTypeService mts = getMedicalTypeServiceById(id);
        mts.setState(true);
        medicalServiceService.changeStateByMedicalTypeService(mts,true);
        medicalTypeServiceRepository.save(mts);
    }

    @Override
    public List<MedicalTypeService> getAllMedicalTypeService() {
        return medicalTypeServiceRepository.findAll();
    }
}
