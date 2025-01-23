package com.arcadia.clinica.service.business_services.imp;

import com.arcadia.clinica.entity.business_services.Specialty;
import com.arcadia.clinica.repository.business_services.ISpecialityRepository;
import com.arcadia.clinica.service.business_services.ISpecialtyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyService implements ISpecialtyService {

    private final ISpecialityRepository specialityRepository;

    public SpecialtyService(ISpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Specialty save(Specialty specialty) {
        return this.specialityRepository.save(specialty);
    }

    @Override
    public List<Specialty> findAll() {
        return this.specialityRepository.findAll();
    }

    @Override
    public Optional<Specialty> findSpecialtyByName(String name) {
        Optional<Specialty> specialty = this.specialityRepository.findSpecialtyByName(name);
        if (specialty.isPresent()) {
            return specialty;
        }
        throw new IllegalArgumentException("No se encontro el tipo de specialidad");
    }

    @Override
    public void delete(Integer id) {
        this.specialityRepository.deleteById(id);
    }
}
