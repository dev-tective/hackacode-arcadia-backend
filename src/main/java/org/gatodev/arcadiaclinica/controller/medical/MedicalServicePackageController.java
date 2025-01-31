package org.gatodev.arcadiaclinica.controller.medical;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServicePackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical-service-package")
public class MedicalServicePackageController {

    private final IMedicalServicePackageService medicalServicePackageService;

    public MedicalServicePackageController(IMedicalServicePackageService medicalServicePackageService) {
        this.medicalServicePackageService = medicalServicePackageService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(medicalServicePackageService.getAllMedicalService()
                .stream()
                .map(medicalServicePackageService::convertToDTO)
                .toList());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody MedicalServicePackage medicalServicePackage) {
        MedicalServicePackage msp = medicalServicePackageService.addMedicalServicePackage(medicalServicePackage);
        return ResponseEntity.ok(medicalServicePackageService.convertToDTO(msp));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody MedicalServicePackage medicalServicePackage) {
        MedicalServicePackage msp = medicalServicePackageService.updateMedicalServicePackage(medicalServicePackage);
        return ResponseEntity.ok(medicalServicePackageService.convertToDTO(msp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        MedicalServicePackage msp = medicalServicePackageService.getMedicalServicePackageById(id);
        return ResponseEntity.ok(medicalServicePackageService.convertToDTO(msp));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivate(@PathVariable long id) {
        medicalServicePackageService.deleteMedicalServicePackageById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<?> activate(@PathVariable long id) {
        medicalServicePackageService.restoreMedicalServicePackageById(id);
        return ResponseEntity.noContent().build();
    }
}
