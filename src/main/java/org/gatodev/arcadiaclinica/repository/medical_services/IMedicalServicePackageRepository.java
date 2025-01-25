package org.gatodev.arcadiaclinica.repository.medical_services;

import org.gatodev.arcadiaclinica.entity.medical_services.MedicalServicePackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicalServicePackageRepository extends JpaRepository<MedicalServicePackage, Integer> {
}
