package org.gatodev.arcadiaclinica.repository.medical_services;

import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IMedicalServicePackageRepository extends JpaRepository<MedicalServicePackage, Long> {
    List<MedicalServicePackage> findAllByMedicalServicesContaining(MedicalService medicalService);
    boolean existsByCode(String code);
    boolean existsByName(String name);
}
