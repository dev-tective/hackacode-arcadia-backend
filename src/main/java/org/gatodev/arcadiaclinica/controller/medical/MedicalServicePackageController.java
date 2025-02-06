package org.gatodev.arcadiaclinica.controller.medical;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.DTO.medical.MedicalServicePackageRequest;
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
        return ResponseEntity.ok(medicalServicePackageService.getAllMedicalServicePackages());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody MedicalServicePackageRequest request) {
        return ResponseEntity.ok(medicalServicePackageService.addMedicalServicePackage(request));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody MedicalServicePackage msp) {
        return ResponseEntity.ok(medicalServicePackageService.updateMedicalServicePackage(msp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(medicalServicePackageService.getMedicalServicePackageById(id));
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
