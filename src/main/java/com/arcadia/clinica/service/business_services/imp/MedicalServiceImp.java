package com.arcadia.clinica.service.business_services.imp;

import com.arcadia.clinica.DTOS.business_services.MedicalServiceDTO;
import com.arcadia.clinica.repository.business_services.IMedicalServiceRepository;
import com.arcadia.clinica.service.business_services.IMedicalService;

import java.util.List;
import java.util.Optional;

public class MedicalServiceImp implements IMedicalService {

    private final IMedicalServiceRepository medicalServiceRepository;

    public MedicalServiceImp(IMedicalServiceRepository medicalServiceRepository) {
        this.medicalServiceRepository = medicalServiceRepository;
    }

    @Override
    public Optional<MedicalServiceDTO> save(MedicalServiceDTO medicalServiceDto) {
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Optional<MedicalServiceDTO> getMedicalService(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<MedicalServiceDTO> getAllMedicalServices() {
        return List.of();
    }
}
