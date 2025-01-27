package org.gatodev.arcadiaclinica.controller.medical;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalSpecialtyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical-specialty")
public class MedicalSpecialtyController {

    private final IMedicalSpecialtyService medicalSpecialtyService;

    public MedicalSpecialtyController(IMedicalSpecialtyService medicalSpecialtyService) {
        this.medicalSpecialtyService = medicalSpecialtyService;
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody MedicalSpecialty medicalSpecialty) {
        return ResponseEntity.ok(medicalSpecialtyService.addMedicalSpecialty(medicalSpecialty));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody MedicalSpecialty medicalSpecialty) {
        return ResponseEntity.ok(medicalSpecialtyService.updateMedicalSpecialty(medicalSpecialty));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(medicalSpecialtyService.getAllMedicalSpecialty());
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivate(@PathVariable Long id) {
        medicalSpecialtyService.deactivateMedicalSpecialtyById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<?> activate(@PathVariable Long id) {
        medicalSpecialtyService.activateMedicalSpecialtyById(id);
        return ResponseEntity.noContent().build();
    }
}
