package org.gatodev.arcadiaclinica.service.medical.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;
import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.gatodev.arcadiaclinica.entity.medical.MedicalTypeService;
import org.gatodev.arcadiaclinica.repository.medical_services.IMedicalServiceRepository;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServicePackageService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServiceService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicalServiceServiceImpl implements IMedicalServiceService {

    private final IMedicalServiceRepository medicalServiceRepository;

    public MedicalServiceServiceImpl(
            IMedicalServiceRepository medicalServiceRepository,
            IMedicalServicePackageService medicalServicePackageService
    ) {
        this.medicalServiceRepository = medicalServiceRepository;
    }

    @Override
    public MedicalService addMedicalService(MedicalService medicalService) {
        return medicalServiceRepository.save(medicalService);
    }

    @Override
    public MedicalService updateMedicalService(MedicalService medicalService) {
        if (!medicalServiceRepository.existsById(medicalService.getId())) {
            throw new EntityNotFoundException("Medical service not found");
        }
        medicalService.setState(true);
        return medicalServiceRepository.save(medicalService);
    }

    private void validateMedicalService(String code, String name) {

    }

    @Override
    public MedicalService getMedicalServiceById(Long id) {
        return medicalServiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical Service not found"));
    }

    @Transactional
    @Override
    public void deactivateMedicalServiceById(Long id) {
        MedicalService medicalService = getMedicalServiceById(id);
        medicalService.setState(false);

        List<MedicalServicePackage> msps = medicalServicePackageService
                .getAllMedicalServicePackagesByMedicalService(medicalService);

        if (msps != null && !msps.isEmpty()) {
            msps.forEach(msp -> {
               if (msp.getState()) medicalServicePackageService
                       .deleteMedicalServicePackageById(msp.getId());
            });
        }

        medicalServiceRepository.save(medicalService);
    }

    @Transactional
    @Override
    public void activateMedicalServiceById(Long id) {
        MedicalService medicalService = getMedicalServiceById(id);
        medicalService.setState(false);

        List<MedicalServicePackage> msps = medicalServicePackageService
                .getAllMedicalServicePackagesByMedicalService(medicalService);

        if (msps != null && !msps.isEmpty()) {
            msps.forEach(msp -> medicalServicePackageService
                    .restoreMedicalServicePackageById(msp.getId()));
        }

        medicalServiceRepository.save(medicalService);
    }

    @Override
    public void existMedicalService(String name, String code) {
        if (medicalServiceRepository.existsByCodeOrName(name, code)) {
            throw new DataIntegrityViolationException("Name or code already exists");
        }
    }

    @Transactional
    @Override
    public void changeStateByMedicalTypeService(MedicalTypeService mts, boolean state) {
        medicalServiceRepository.saveAll(getAllMedicalService()
                .stream()
                .filter(ms -> ms.getMedicalTypeService().getId().equals(mts.getId()))
                .peek(ms -> {
                    if (state) {
                        activateMedicalServiceById(ms.getId());
                    } else {
                        deactivateMedicalServiceById(ms.getId());
                    }
                }).toList());
    }

    @Transactional
    @Override
    public void changeStateByMedicalSpecialty(MedicalSpecialty msp, boolean state) {
        medicalServiceRepository.saveAll(getAllMedicalService()
                .stream()
                .filter(ms -> ms.getMedicalSpecialty().getId().equals(msp.getId()))
                .peek(ms -> {
                    if (state) {
                        activateMedicalServiceById(ms.getId());
                    } else {
                        deactivateMedicalServiceById(ms.getId());
                    }
                }).toList());
    }

    @Override
    public List<MedicalService> getAllMedicalService() {
        return medicalServiceRepository.findAll();
    }
}
