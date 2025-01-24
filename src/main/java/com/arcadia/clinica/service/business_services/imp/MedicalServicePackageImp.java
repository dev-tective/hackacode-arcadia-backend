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
    public Optional<MedicalServicePackage> findById(Integer idService) {
        Optional<MedicalServicePackage> medicalServicePackage = this.medicalServicePackageRepository.findById(idService);
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
    public void delete(Integer idService) {
        this.medicalServicePackageRepository.deleteById(idService);
    }

    @Override
    public MedicalServicePackage update(Integer idService, MedicalServicePackageDTO entity) {
        Optional<MedicalServicePackage> optionalMedicalServicePackage = this.medicalServicePackageRepository.findById(idService);

        if (optionalMedicalServicePackage.isPresent()) {
            MedicalServicePackage existingPackage = optionalMedicalServicePackage.get();

            existingPackage.setCode(entity.getCode());
            existingPackage.setPrice(entity.getPrice());
            existingPackage.setAvailable(entity.getAvailable());
            existingPackage.setNotAvailable(entity.getNotAvailable());
            existingPackage.setStatus(entity.getStatus());

            if (entity.getMedicalServices() != null && !entity.getMedicalServices().isEmpty()) {
                List<MedicalService> updatedMedicalServices = this.medicalService.findAll();
                existingPackage.setMedicalServices(updatedMedicalServices);
            }

            return this.medicalServicePackageRepository.save(existingPackage);
        } else {
            throw new RuntimeException("medical service package with ID " + idService + " not found.");
        }
    }

}
