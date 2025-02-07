package org.gatodev.arcadiaclinica.service.medical;

import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;
import java.util.List;

public interface IMedicalServicePackageService {
    MedicalServicePackage addMedicalServicePackage(MedicalServicePackage medicalServicePackage);

    MedicalServicePackage updateMedicalServicePackage(MedicalServicePackage medicalServicePackage);

    MedicalServicePackage getMedicalServicePackageById(long id);

    List<MedicalServicePackage> getAllMedicalServicePackages();

    List<MedicalServicePackage> getAllMedicalServicePackagesByMedicalService(MedicalService medicalService);

    void deactivateMedicalServicePackage(long id);

    void reactivateMedicalServicePackage(MedicalServicePackage medicalServicePackage);

    void activateMedicalServicePackage(long id);
}
