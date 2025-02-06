package org.gatodev.arcadiaclinica.service.medical.impl;

import org.gatodev.arcadiaclinica.DTO.medical.MedicalServicePackageRequest;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;
import org.gatodev.arcadiaclinica.repository.medical_services.IMedicalServicePackageRepository;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServicePackageService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServiceService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@Service
public class MedicalServicePackageServiceImpl implements IMedicalServicePackageService {

    private final IMedicalServicePackageRepository medicalServicePackageRepository;
    private final MedicalServiceMapper medicalServiceMapper;
    private final IMedicalServiceService medicalServiceService;

    public MedicalServicePackageServiceImpl(
            IMedicalServicePackageRepository medicalServicePackageRepository,
            MedicalServiceMapper medicalServiceMapper,
            @Lazy
            IMedicalServiceService medicalServiceService
    ) {
        this.medicalServicePackageRepository = medicalServicePackageRepository;
        this.medicalServiceMapper = medicalServiceMapper;
        this.medicalServiceService = medicalServiceService;
    }

    @Override
    public MedicalServicePackage addMedicalServicePackage(MedicalServicePackageRequest request) {
        validateMedicalService(request.code(), request.name());

        MedicalServicePackage medicalServicePackage = new MedicalServicePackage();
        List<MedicalService> mss = request.idMedicalServices()
                .stream().map(medicalServiceService::getMedicalServiceById).toList();

        getFieldsPriceAndDuration(mss, medicalServicePackage);
        medicalServicePackage.setCode(request.code());
        medicalServicePackage.setDescription(request.description());
        medicalServicePackage.setAvailable(request.available());
        medicalServicePackage.setNotAvailable(request.notAvailable());
        medicalServicePackage.setName(request.name());
        medicalServicePackage.setMedicalServices(mss);
        return medicalServicePackageRepository.save(medicalServicePackage);
    }

    @Override
    public MedicalServicePackage updateMedicalServicePackage(MedicalServicePackage msp) {
        if (!medicalServicePackageRepository.existsById(msp.getId())) {
            throw new RuntimeException("Medical service package with id " + msp.getId() + " does not exist");
        }
        if (msp.getCode().length() != 6) {
            throw new RuntimeException("Medical service package code must be 6 characters");
        }
        getFieldsPriceAndDuration(msp.getMedicalServices(), msp);
        msp.setState(true);
        return medicalServicePackageRepository.save(msp);
    }

    private void validateMedicalService(String code, String name) {
        if (code.length() != 6) {
            throw new RuntimeException("Medical service package code must be 6 characters");
        }
        if (medicalServicePackageRepository.existsByCode(code)) {
            throw new RuntimeException("Medical service package code already exists");
        }
        if (medicalServicePackageRepository.existsByName(name)) {
            throw new RuntimeException("Medical service package name already exists");
        }
    }

    private void getFieldsPriceAndDuration(List<MedicalService> mss, MedicalServicePackage msp) {
        boolean specialtyMatch = !mss.isEmpty() && mss.stream()
                .allMatch(ms -> ms.getMedicalSpecialty().getId()
                        .equals(mss.get(0).getMedicalSpecialty().getId()));
        if (!specialtyMatch) {
            throw new RuntimeException("All medical services must be of the same specialty");
        }

        Duration duration = mss.stream().map(MedicalService::getDuration)
                .reduce(Duration.ZERO, Duration::plus);

        BigDecimal price = mss.stream().map(MedicalService::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        msp.setPrice(price);
        msp.setDuration(duration);
    }

    @Override
    public MedicalServicePackage getMedicalServicePackageById(Long id) {
        return medicalServicePackageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical Service Package not found"));
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
        boolean allActive = msp.getMedicalServices().stream()
                .allMatch(MedicalService::getState);

        if (!allActive) {
            throw new IllegalStateException("Some medical services are not active, cannot restore package.");
        }

        msp.setState(true);
        medicalServicePackageRepository.save(msp);
    }

    @Override
    public List<MedicalServicePackage> getAllMedicalServicePackages() {
        return medicalServicePackageRepository.findAll();
    }

    @Override
    public List<MedicalServicePackage> getAllMedicalServicePackagesByMedicalService(MedicalService medicalService) {
        return medicalServicePackageRepository.findAllByMedicalServicesContaining(medicalService);
    }
}
