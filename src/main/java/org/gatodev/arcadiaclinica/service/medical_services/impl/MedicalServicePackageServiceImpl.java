package org.gatodev.arcadiaclinica.service.medical_services.impl;

import org.gatodev.arcadiaclinica.entity.medical_services.BaseAttributes;
import org.gatodev.arcadiaclinica.entity.medical_services.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical_services.MedicalServicePackage;
import org.gatodev.arcadiaclinica.repository.medical_services.IMedicalServicePackageRepository;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalServicePackageService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalServicePackageServiceImpl implements IMedicalServicePackageService {

    private final IMedicalServicePackageRepository medicalServicePackageRepository;

    public MedicalServicePackageServiceImpl(IMedicalServicePackageRepository medicalServicePackageRepository) {
        this.medicalServicePackageRepository = medicalServicePackageRepository;
    }

    @Override
    public MedicalServicePackage addMedicalServicePackage(MedicalServicePackage msp) {
        msp.validateCode();
        String serviceCodes = msp.getServices().stream()
                .map(BaseAttributes::getCode)
                .collect(Collectors.joining()) + msp.getCode();
        msp.setCode(serviceCodes);

        Duration totalDuration = msp.getServices().stream()
                .map(MedicalService::getDuration)
                .reduce(Duration.ZERO, Duration::plus);

        msp.setDuration(totalDuration);
        return medicalServicePackageRepository.save(msp);
    }

    @Override
    public MedicalServicePackage updateMedicalServicePackage(MedicalServicePackage msp) {
        return null;
    }

    @Override
    public MedicalServicePackage getMedicalServicePackageById(int id) {
        return medicalServicePackageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical Service Package not found"));
    }

    @Override
    public void desactivateMedicalServicePackageById(int id) {
        MedicalServicePackage msp = getMedicalServicePackageById(id);

        boolean hasInactiveService = msp.getServices().stream()
                .anyMatch(service -> !service.getState());

        if (hasInactiveService) {
            msp.setState(false);
            medicalServicePackageRepository.save(msp);
        }
    }


    @Override
    public void activateMedicalServicePackageById(int id) {
        MedicalServicePackage msp = getMedicalServicePackageById(id);

        boolean allActive = msp.getServices().stream()
                .allMatch(MedicalService::getState);

        msp.setState(allActive);
        medicalServicePackageRepository.save(msp);
    }

    @Override
    public void deleteMedicalServicePackageById(int id) {
        MedicalServicePackage msp = getMedicalServicePackageById(id);
        msp.setState(false);
        medicalServicePackageRepository.save(msp);
    }

    @Override
    public void restoreMedicalServicePackageById(int id) {
        MedicalServicePackage msp = getMedicalServicePackageById(id);

        boolean allActive = msp.getServices().stream()
                .allMatch(MedicalService::getState);

        if (!allActive) {
            throw new IllegalStateException("Some medical services are not active, cannot restore package.");
        }

        msp.setState(true);
        medicalServicePackageRepository.save(msp);
    }

    @Override
    public List<MedicalServicePackage> getAllMedicalService() {
        return medicalServicePackageRepository.findAll();
    }
}
