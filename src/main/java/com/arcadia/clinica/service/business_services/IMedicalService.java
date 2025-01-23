package com.arcadia.clinica.service.business_services;

import com.arcadia.clinica.DTOS.business_services.MedicalServiceDTO;

import java.util.List;
import java.util.Optional;

public interface IMedicalService {

    //save medical service
    Optional<MedicalServiceDTO> save(MedicalServiceDTO medicalServiceDto);
    //delete medical service
    void delete(Integer id);
    //get medical service
    Optional<MedicalServiceDTO> getMedicalService(Integer id);
    //get all medical services
    List<MedicalServiceDTO> getAllMedicalServices();


}
