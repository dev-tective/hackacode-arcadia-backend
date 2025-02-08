package org.gatodev.arcadiaclinica.repository.medical;

import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMedicalServicePackageRepository extends JpaRepository<MedicalServicePackage, Long> {
    List<MedicalServicePackage> findAllByMedicalServicesContaining(MedicalService medicalService);
    @Query("""
    SELECT p FROM MedicalServicePackage p
    WHERE (SELECT COUNT(ms) FROM p.medicalServices ms WHERE ms IN :medicalServices) = SIZE(p.medicalServices)
    AND SIZE(p.medicalServices) = :size""")
    Optional<MedicalServicePackage> findExactPackage(@Param("medicalServices") List<MedicalService> medicalServices, @Param("size") int size);
}
