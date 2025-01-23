package com.arcadia.clinica.service.business_services.imp;

import com.arcadia.clinica.DTOS.business_services.MedicalServicePackageDTO;
import com.arcadia.clinica.entity.business_services.MedicalService;
import com.arcadia.clinica.entity.business_services.MedicalServicePackage;
import com.arcadia.clinica.repository.business_services.IMedicalServicePackageRepository;
import com.arcadia.clinica.service.business_services.IMedicalService;
import com.arcadia.clinica.service.business_services.IMedicalServicePackage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MedicalServicePackageImp implements IMedicalServicePackage {

    private final IMedicalServicePackageRepository medicalServicePackageRepository;
    private final IMedicalService medicalService;
    public MedicalServicePackageImp(IMedicalServicePackageRepository medicalServicePackageRepository,
                                    IMedicalService medicalService) {
        this.medicalServicePackageRepository = medicalServicePackageRepository;
        this.medicalService = medicalService;
    }



    @Override
    public MedicalServicePackage save(MedicalServicePackageDTO entity) {

        List<MedicalService> listMedicalService = this.medicalService.findAll();

        MedicalServicePackage medicalServicePackage = MedicalServicePackage.builder()
                .code(entity.getCode())
                .price(entity.getPrice())
                .available(entity.getAvailable())
                .notAvailable(entity.getNotAvailable())
                .status(entity.getStatus())
                .medicalServices(listMedicalService)
                .build();
        return this.medicalServicePackageRepository.save(medicalServicePackage);
    }

    @Override
    public Optional<MedicalServicePackage> findById(Integer id_service) {
        Optional<MedicalServicePackage> medicalServicePackage = this.medicalServicePackageRepository.findById(id_service);
        if(medicalServicePackage.isPresent()){
            return medicalServicePackage;
        }
        throw new RuntimeException("not found");
    }

    @Override
    public List<MedicalServicePackage> findAll() {
        return this.medicalServicePackageRepository.findAll();
    }

    @Override
    public void delete(Integer id_service) {
        this.medicalServicePackageRepository.deleteById(id_service);
    }
}
