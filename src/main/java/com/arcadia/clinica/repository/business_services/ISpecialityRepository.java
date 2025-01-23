package com.arcadia.clinica.repository.business_services;

import com.arcadia.clinica.entity.business_services.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpecialityRepository extends JpaRepository<Specialty, Integer> {
}
