package org.gatodev.arcadiaclinica.service.medical_services;

import org.gatodev.arcadiaclinica.DTO.medical.MedicalServicePackageDTO;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;

import java.util.List;

public interface IMedicalServicePackageService {
    MedicalServicePackage addMedicalServicePackage(MedicalServicePackage medicalService);

    MedicalServicePackage updateMedicalServicePackage(MedicalServicePackage medicalService);

    MedicalServicePackage getMedicalServicePackageById(Long id);

    void deactivateMedicalServicePackageById(Long id);

    void activateMedicalServicePackageById(Long id);

    void deleteMedicalServicePackageById(Long id);

    void restoreMedicalServicePackageById(Long id);

    MedicalServicePackageDTO convertToDTO(MedicalServicePackage medicalServicePackage);

    List<MedicalServicePackage> getAllMedicalService();
}
