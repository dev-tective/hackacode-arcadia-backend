package org.gatodev.arcadiaclinica.service.medical_services;

import org.gatodev.arcadiaclinica.entity.medical_services.MedicalServicePackage;
import org.gatodev.arcadiaclinica.entity.medical_services.MedicalSpecialty;
import org.gatodev.arcadiaclinica.entity.medical_services.MedicalTypeService;
import java.util.List;

public interface IMedicalServicePackageService {
    MedicalServicePackage addMedicalServicePackage(MedicalServicePackage medicalService);

    MedicalServicePackage updateMedicalServicePackage(MedicalServicePackage medicalService);

    MedicalServicePackage getMedicalServicePackageById(int id);

    void desactivateMedicalServicePackageById(int id);

    void activateMedicalServicePackageById(int id);

    void deleteMedicalServicePackageById(int id);

    void restoreMedicalServicePackageById(int id);

    List<MedicalServicePackage> getAllMedicalService();
}
