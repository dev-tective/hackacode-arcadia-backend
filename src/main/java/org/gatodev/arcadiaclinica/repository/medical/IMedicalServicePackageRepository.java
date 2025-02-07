package org.gatodev.arcadiaclinica.repository.medical;

import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMedicalServicePackageRepository extends JpaRepository<MedicalServicePackage, Long> {
    List<MedicalServicePackage> findAllByMedicalServicesContaining(MedicalService medicalService);
}
