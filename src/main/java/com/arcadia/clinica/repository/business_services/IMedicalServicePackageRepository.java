package com.arcadia.clinica.repository.business_services;

import com.arcadia.clinica.entity.business_services.MedicalServicePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicalServicePackageRepository extends JpaRepository<MedicalServicePackage, Integer> {
}
