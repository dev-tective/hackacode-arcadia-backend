package org.gatodev.arcadiaclinica.service.medical.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;
import org.gatodev.arcadiaclinica.repository.medical.IMedicalServicePackageRepository;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServicePackageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MedicalServicePackageServiceImpl implements IMedicalServicePackageService {

    private final IMedicalServicePackageRepository medicalServicePackageRepository;

    public MedicalServicePackageServiceImpl(IMedicalServicePackageRepository medicalServicePackageRepository) {
        this.medicalServicePackageRepository = medicalServicePackageRepository;
    }

    @Override
    public MedicalServicePackage addMedicalServicePackage(MedicalServicePackage medicalServicePackage) {
        return medicalServicePackageRepository.save(medicalServicePackage);
    }

    @Override
    public MedicalServicePackage updateMedicalServicePackage(MedicalServicePackage medicalServicePackage) {
        return medicalServicePackageRepository.save(medicalServicePackage);
    }

    @Override
    public MedicalServicePackage getMedicalServicePackageById(long id) {
        return medicalServicePackageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical Service Package not found"));
    }

    @Override
    public List<MedicalServicePackage> getAllMedicalServicePackages() {
        return medicalServicePackageRepository.findAll();
    }

    @Override
    public List<MedicalServicePackage> getAllMedicalServicePackagesByMedicalService(MedicalService medicalService) {
        return medicalServicePackageRepository.findAllByMedicalServicesContaining(medicalService);
    }

    @Override
    public void deactivateMedicalServicePackage(long id) {
        MedicalServicePackage medicalServicePackage = getMedicalServicePackageById(id);
        medicalServicePackage.setState(false);
        medicalServicePackageRepository.save(medicalServicePackage);
    }

    @Transactional
    @Override
    public void reactivateMedicalServicePackage(MedicalServicePackage medicalServicePackage) {
        List<MedicalService> medicalServices = medicalServicePackage.getMedicalServices();
        medicalServicePackage.setState(medicalServices.stream().allMatch(MedicalService::getState));
        medicalServicePackageRepository.save(medicalServicePackage);
    }

    @Transactional
    @Override
    public void activateMedicalServicePackage(long id) {
        reactivateMedicalServicePackage(getMedicalServicePackageById(id));
    }
}
