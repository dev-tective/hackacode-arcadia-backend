package com.arcadia.clinica.repository.business_services;

import com.arcadia.clinica.entity.business_services.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISpecialityRepository extends JpaRepository<Specialty, Integer> {
    Optional<Specialty> findSpecialtyByName(String name);
}
