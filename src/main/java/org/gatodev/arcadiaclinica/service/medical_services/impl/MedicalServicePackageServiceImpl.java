package org.gatodev.arcadiaclinica.service.medical_services.impl;

import org.gatodev.arcadiaclinica.DTO.medical.MedicalServiceMapper;
import org.gatodev.arcadiaclinica.DTO.medical.MedicalServicePackageDTO;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;
import org.gatodev.arcadiaclinica.repository.medical_services.IMedicalServicePackageRepository;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalServicePackageService;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.List;

@Service
public class MedicalServicePackageServiceImpl implements IMedicalServicePackageService {

    private final IMedicalServicePackageRepository medicalServicePackageRepository;
    private final MedicalServiceMapper medicalServiceMapper;

    public MedicalServicePackageServiceImpl(
            IMedicalServicePackageRepository medicalServicePackageRepository, MedicalServiceMapper medicalServiceMapper
    ) {
        this.medicalServicePackageRepository = medicalServicePackageRepository;
        this.medicalServiceMapper = medicalServiceMapper;
    }

    @Override
    public MedicalServicePackage addMedicalServicePackage(MedicalServicePackage msp) {
        validateMedicalService(msp);
        return medicalServicePackageRepository.save(msp);
    }


    @Override
    public MedicalServicePackage updateMedicalServicePackage(MedicalServicePackage msp) {
        if (!medicalServicePackageRepository.existsById(msp.getId())) {
            throw new RuntimeException("Medical service package with id " + msp.getId() + " does not exist");
        }

        validateMedicalService(msp);
        msp.setState(true);
        return medicalServicePackageRepository.save(msp);
    }

    private void validateMedicalService(MedicalServicePackage msp) {
        msp.validateCode();

        if (msp.getMedicalServices() == null || msp.getMedicalServices().isEmpty()) {
            throw new RuntimeException("Medical Service Package is Empty or Null");
        }

        List<MedicalService> medicalServices = msp.getMedicalServices();
        if (medicalServices.stream().anyMatch(service -> !service.getMedicalSpecialty()
                .equals(medicalServices.get(0).getMedicalSpecialty()))) {
            throw new RuntimeException("Medical services do not match");
        }

        Duration totalDuration = msp.getMedicalServices()
                .stream()
                .map(MedicalService::getDuration)
                .reduce(Duration.ZERO, Duration::plus);

        msp.setDuration(totalDuration);
    }

    @Override
    public MedicalServicePackage getMedicalServicePackageById(Long id) {
        return medicalServicePackageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical Service Package not found"));
    }

    @Override
    public void deactivateMedicalServicePackageById(Long id) {
        MedicalServicePackage msp = getMedicalServicePackageById(id);

        boolean hasInactiveService = msp.getMedicalServices()
                .stream()
                .anyMatch(service -> !service.getState());

        if (hasInactiveService) {
            msp.setState(false);
            medicalServicePackageRepository.save(msp);
        }
    }


    @Override
    public void activateMedicalServicePackageById(Long id) {
        MedicalServicePackage msp = getMedicalServicePackageById(id);

        boolean allActive = msp.getMedicalServices()
                .stream()
                .allMatch(MedicalService::getState);

        msp.setState(allActive);
        medicalServicePackageRepository.save(msp);
    }

    @Override
    public void deleteMedicalServicePackageById(Long id) {
        MedicalServicePackage msp = getMedicalServicePackageById(id);
        msp.setState(false);
        medicalServicePackageRepository.save(msp);
    }

    @Override
    public void restoreMedicalServicePackageById(Long id) {
        MedicalServicePackage msp = getMedicalServicePackageById(id);

        boolean allActive = msp.getMedicalServices()
                .stream()
                .allMatch(MedicalService::getState);

        if (!allActive) {
            throw new IllegalStateException("Some medical services are not active, cannot restore package.");
        }

        msp.setState(true);
        medicalServicePackageRepository.save(msp);
    }

    @Override
    public MedicalServicePackageDTO convertToDTO(MedicalServicePackage medicalServicePackage) {
        return medicalServiceMapper.toMedicalServicePackageDTO(medicalServicePackage);
    }

    @Override
    public List<MedicalServicePackage> getAllMedicalService() {
        return medicalServicePackageRepository.findAll();
    }
}
