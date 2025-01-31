package org.gatodev.arcadiaclinica.service.medical.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.DTO.medical.MedicalServiceDTO;
import org.gatodev.arcadiaclinica.DTO.medical.MedicalServiceMapper;
import org.gatodev.arcadiaclinica.DTO.medical.MedicalServiceWPDTO;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;
import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.gatodev.arcadiaclinica.entity.medical.MedicalTypeService;
import org.gatodev.arcadiaclinica.repository.medical_services.IMedicalServiceRepository;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServicePackageService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MedicalServiceServiceImpl implements IMedicalServiceService {

    private final IMedicalServiceRepository medicalServiceRepository;
    private final IMedicalServicePackageService medicalServicePackageService;
    private final MedicalServiceMapper medicalServiceMapper;

    public MedicalServiceServiceImpl(
            IMedicalServiceRepository medicalServiceRepository,
            IMedicalServicePackageService medicalServicePackageService, MedicalServiceMapper medicalServiceMapper) {
        this.medicalServiceRepository = medicalServiceRepository;
        this.medicalServicePackageService = medicalServicePackageService;
        this.medicalServiceMapper = medicalServiceMapper;
    }

    @Override
    public MedicalService addMedicalService(MedicalService ms) {
        ms.validateCode();
        return medicalServiceRepository.save(ms);
    }

    @Override
    public MedicalService updateMedicalService(MedicalService ms) {
        if (!medicalServiceRepository.existsById(ms.getId())) {
            throw new EntityNotFoundException("Medical service with id " + ms.getId() + " does not exist");
        }
        ms.validateCode();
        ms.setState(true);
        return medicalServiceRepository.save(ms);
    }

    @Override
    public MedicalService getMedicalServiceById(Long id) {
        return medicalServiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical service with id " + id + " not found"));
    }

    @Transactional
    @Override
    public void deactivateMedicalServiceById(Long id) {
        MedicalService medicalService = getMedicalServiceById(id);
        medicalService.setState(false);

        List<MedicalServicePackage> msp = medicalService.getMedicalServicePackages();

        if (msp != null && !msp.isEmpty()) {
            msp.forEach(ps ->
                    medicalServicePackageService.deactivateMedicalServicePackageById(ps.getId()));
        }

        medicalServiceRepository.save(medicalService);
    }

    @Transactional
    @Override
    public void activateMedicalServiceById(Long id) {
        MedicalService medicalService = getMedicalServiceById(id);
        medicalService.setState(true);

        List<MedicalServicePackage> msp = medicalService.getMedicalServicePackages();

        if (msp != null && !msp.isEmpty()) {
            msp.forEach(ps ->
                    medicalServicePackageService.activateMedicalServicePackageById(ps.getId()));
        }

        medicalServiceRepository.save(medicalService);
    }

    @Transactional
    @Override
    public void changeStateByMedicalTypeService(MedicalTypeService medicalTypeService, boolean state) {
        medicalServiceRepository.saveAll(getAllMedicalService()
                .stream()
                .filter(ms -> ms.getMedicalTypeService().equals(medicalTypeService))
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
    public void changeStateByMedicalSpecialty(MedicalSpecialty medicalSpecialty, boolean state) {
        medicalServiceRepository.saveAll(getAllMedicalService()
                .stream()
                .filter(ms -> ms.getMedicalSpecialty().equals(medicalSpecialty))
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

    @Override
    public MedicalServiceDTO convertToDTO(MedicalService medicalService) {
        return medicalServiceMapper.toMedicalServiceDTO(medicalService);
    }

    @Override
    public MedicalServiceWPDTO convertToWPDTO(MedicalService medicalService) {
        return medicalServiceMapper.toMedicalServiceWPDTO(medicalService);
    }
}
