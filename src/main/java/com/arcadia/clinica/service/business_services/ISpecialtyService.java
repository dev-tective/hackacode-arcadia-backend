package com.arcadia.clinica.service.business_services;

import com.arcadia.clinica.entity.business_services.Specialty;

import java.util.List;
import java.util.Optional;

public interface ISpecialtyService {

    Specialty save(Specialty specialty);
    List<Specialty> findAll();
    Optional<Specialty> findSpecialtyByName(String name);
    void delete(Integer id);
    Specialty update(Specialty specialty, Integer id);

}
