package org.gatodev.arcadiaclinica.service.medical.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;
import org.gatodev.arcadiaclinica.repository.medical.IMedicalServiceRepository;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServicePackageService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServiceS;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MedicalServiceSImpl implements IMedicalServiceS {

    private final IMedicalServiceRepository medicalServiceRepository;
    private final IMedicalServicePackageService medicalServicePackageService;

    public MedicalServiceSImpl(
            IMedicalServiceRepository medicalServiceRepository,
            IMedicalServicePackageService medicalServicePackageService
    ) {
        this.medicalServiceRepository = medicalServiceRepository;
        this.medicalServicePackageService = medicalServicePackageService;
    }

    @Override
    public MedicalService addMedicalService(MedicalService medicalService) {
        return medicalServiceRepository.save(medicalService);
    }

    @Override
    public MedicalService updateMedicalService(MedicalService medicalService) {
        medicalService.setState(true);
        return medicalServiceRepository.save(medicalService);
    }

    @Override
    public MedicalService getMedicalServiceById(long id) {
        return medicalServiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical Service not found"));
    }

    @Override
    public List<MedicalService> getAllMedicalServices() {
        return medicalServiceRepository.findAll();
    }

    @Override
    public void deactivateMedicalService(long id) {
        MedicalService medicalService = getMedicalServiceById(id);
        medicalService.setState(false);
        medicalServiceRepository.save(medicalService);
    }

    @Transactional
    @Override
    public void activateMedicalService(long id) {
        MedicalService ms = getMedicalServiceById(id);
        ms.setState(ms.getMedicalTypeService().getState() && ms.getMedicalSpecialty().getState());
        medicalServiceRepository.save(ms);
        List<MedicalServicePackage> medicalServicePackages = medicalServicePackageService
                .getAllMedicalServicePackagesByMedicalService(ms);
        medicalServicePackages.forEach(medicalServicePackageService::reactivateMedicalServicePackage);
    }
}
