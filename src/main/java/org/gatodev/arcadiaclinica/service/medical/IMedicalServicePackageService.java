package org.gatodev.arcadiaclinica.service.medical;

import org.gatodev.arcadiaclinica.DTO.medical.MedicalServicePackageRequest;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;

import java.util.List;

public interface IMedicalServicePackageService {
    MedicalServicePackage addMedicalServicePackage(MedicalServicePackageRequest request);

    MedicalServicePackage updateMedicalServicePackage(MedicalServicePackage medicalService);

    MedicalServicePackage getMedicalServicePackageById(Long id);

    void deleteMedicalServicePackageById(Long id);

    void restoreMedicalServicePackageById(Long id);

    List<MedicalServicePackage> getAllMedicalServicePackages();

    List<MedicalServicePackage> getAllMedicalServicePackagesByMedicalService(MedicalService medicalService);
}
