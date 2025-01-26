package org.gatodev.arcadiaclinica.service.medical_services.impl;

import org.gatodev.arcadiaclinica.entity.medical_services.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical_services.MedicalServicePackage;
import org.gatodev.arcadiaclinica.entity.medical_services.MedicalSpecialty;
import org.gatodev.arcadiaclinica.entity.medical_services.MedicalTypeService;
import org.gatodev.arcadiaclinica.repository.medical_services.IMedicalServiceRepository;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalServicePackageService;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalServiceService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicalServiceServiceImpl implements IMedicalServiceService {

    private final IMedicalServiceRepository medicalServiceRepository;
    private final IMedicalServicePackageService medicalServicePackageService;

    public MedicalServiceServiceImpl(
            IMedicalServiceRepository medicalServiceRepository,
            IMedicalServicePackageService medicalServicePackageService
    ) {
        this.medicalServiceRepository = medicalServiceRepository;
        this.medicalServicePackageService = medicalServicePackageService;
    }

    @Override
    public MedicalService addMedicalService(MedicalService ms) {
        ms.validateCode();
        return medicalServiceRepository.save(ms);
    }

    @Override
    public MedicalService updateMedicalService(MedicalService ms) {
        if (!medicalServiceRepository.existsById(ms.getId())) {
            throw new RuntimeException("Medical service with id " + ms.getId() + " does not exist");
        }
        ms.validateCode();
        ms.setState(true);
        return medicalServiceRepository.save(ms);
    }

    @Override
    public MedicalService getMedicalServiceById(int id) {
        return medicalServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical service with id " + id + " not found"));
    }

    @Override
    public void desactivateMedicalServiceById(int id) {
        MedicalService medicalService = getMedicalServiceById(id);
        medicalService.setState(false);

        List<MedicalServicePackage> msp = medicalService.getMedicalServicePackages();

        if (msp != null && !msp.isEmpty()) {
            msp.forEach(packageService -> {
                medicalServicePackageService.desactivateMedicalServicePackageById(packageService.getId());
            });
        }

        medicalServiceRepository.save(medicalService);
    }

    @Override
    public void activateMedicalServiceById(int id) {
        MedicalService medicalService = getMedicalServiceById(id);
        medicalService.setState(true);

        List<MedicalServicePackage> msp = medicalService.getMedicalServicePackages();

        if (msp != null && !msp.isEmpty()) {
            msp.forEach(packageService -> {
                medicalServicePackageService.activateMedicalServicePackageById(packageService.getId());
            });
        }

        medicalServiceRepository.save(medicalService);
    }

    @Override
    public void changeStateByMedicalTypeService(MedicalTypeService medicalTypeService, boolean state) {
        medicalServiceRepository.saveAll(getAllMedicalService()
                .stream()
                .filter(ms -> ms.getMedicalTypeService().equals(medicalTypeService))
                .peek(ms -> {
                    if (state) {
                        activateMedicalServiceById(ms.getId());
                    } else {
                        desactivateMedicalServiceById(ms.getId());
                    }
                }).toList());
    }

    @Override
    public void changeStateByMedicalSpecialty(MedicalSpecialty medicalSpecialty, boolean state) {
        medicalServiceRepository.saveAll(getAllMedicalService()
                .stream()
                .filter(ms -> ms.getMedicalSpecialty().equals(medicalSpecialty))
                .peek(ms -> {
                    if (state) {
                        activateMedicalServiceById(ms.getId());
                    } else {
                        desactivateMedicalServiceById(ms.getId());
                    }
                }).toList());
    }

    @Override
    public List<MedicalService> getAllMedicalService() {
        return medicalServiceRepository.findAll();
    }
}
