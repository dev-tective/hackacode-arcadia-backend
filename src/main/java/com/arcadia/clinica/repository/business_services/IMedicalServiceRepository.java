package com.arcadia.clinica.repository.business_services;

import com.arcadia.clinica.entity.business_services.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicalServiceRepository extends JpaRepository<MedicalService, Integer> {

}
