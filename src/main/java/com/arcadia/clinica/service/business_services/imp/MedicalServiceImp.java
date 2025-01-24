package com.arcadia.clinica.service.business_services.imp;

import com.arcadia.clinica.DTOS.business_services.MedicalServiceDTO;
import com.arcadia.clinica.entity.business_services.MedicalService;
import com.arcadia.clinica.entity.business_services.Specialty;
import com.arcadia.clinica.entity.business_services.TypeService;
import com.arcadia.clinica.repository.business_services.IMedicalServiceRepository;
import com.arcadia.clinica.service.business_services.IMedicalService;
import com.arcadia.clinica.service.business_services.ISpecialtyService;
import com.arcadia.clinica.service.business_services.ITypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MedicalServiceImp implements IMedicalService {

    private final IMedicalServiceRepository medicalServiceRepository;
    private final ISpecialtyService specialtyService;
    private final ITypeService typeService;


    public MedicalServiceImp(IMedicalServiceRepository medicalServiceRepository,
                             ISpecialtyService specialtyService,
                             ITypeService typeService) {
        this.medicalServiceRepository = medicalServiceRepository;
        this.specialtyService = specialtyService;
        this.typeService = typeService;
    }

    @Override
    public MedicalService save(MedicalServiceDTO entity) {

        Optional<Specialty> specialtyOptional = this.specialtyService.findSpecialtyByName(entity.getSpecialtyName());
        Specialty specialty = specialtyOptional.orElse(null);

        Optional<TypeService> typeServiceOptional = this.typeService.getTypeByName(entity.getTypeServiceName());
        TypeService typeService = typeServiceOptional.orElse(null);


        MedicalService medicalService = MedicalService.builder()
                .code(entity.getCode())
                .price(entity.getPrice())
                .name(entity.getName())
                .description(entity.getDescription())
                .duration(entity.getDuration())
                .specialty(specialty)
                .typeService(typeService)
                .build();

        return this.medicalServiceRepository.save(medicalService);
    }

    @Override
    public Optional<MedicalService> findById(Integer idService) {
        Optional<MedicalService> medicalService = this.medicalServiceRepository.findById(idService);
        if(medicalService.isPresent()){
            return medicalService;
        }
        throw new RuntimeException("not found");
    }

    @Override
    public List<MedicalService> findAll() {
        return this.medicalServiceRepository.findAll();
    }

    @Override
    public void delete(Integer idService) {
        this.medicalServiceRepository.deleteById(idService);
    }

    @Override
    public MedicalService update(Integer idService, MedicalServiceDTO entity) {
        Optional<MedicalService> optionalMedicalService = this.medicalServiceRepository.findById(idService);

        if (optionalMedicalService.isPresent()) {
            MedicalService existingService = optionalMedicalService.get();

            existingService.setCode(entity.getCode());
            existingService.setPrice(entity.getPrice());
            existingService.setName(entity.getName());
            existingService.setDescription(entity.getDescription());
            existingService.setDuration(entity.getDuration());

            Optional<Specialty> specialtyOptional = this.specialtyService.findSpecialtyByName(entity.getSpecialtyName());
            Specialty specialty = specialtyOptional.orElse(null);
            existingService.setSpecialty(specialty);

            Optional<TypeService> typeServiceOptional = this.typeService.getTypeByName(entity.getTypeServiceName());
            TypeService typeService = typeServiceOptional.orElse(null);
            existingService.setTypeService(typeService);

            return this.medicalServiceRepository.save(existingService);
        } else {
            throw new RuntimeException("medical service with ID " + idService + " not found.");
        }
    }

}
