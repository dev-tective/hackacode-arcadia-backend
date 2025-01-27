package org.gatodev.arcadiaclinica.controller.medical;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.entity.medical.MedicalTypeService;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalTypeServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical-type-service")
public class MedicalTypeServiceController {

    private final IMedicalTypeServiceService medicalTypeServiceService;

    public MedicalTypeServiceController(IMedicalTypeServiceService medicalTypeServiceService) {
        this.medicalTypeServiceService = medicalTypeServiceService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(medicalTypeServiceService.getAllMedicalTypeService());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MedicalTypeService medicalTypeService) {
        return ResponseEntity.ok(medicalTypeServiceService
                .addMedicalTypeService(medicalTypeService));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody MedicalTypeService medicalTypeService) {
        return ResponseEntity.ok(medicalTypeServiceService
                .updateMedicalTypeService(medicalTypeService));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivate(@PathVariable Long id) {
        medicalTypeServiceService.deactivateMedicalTypeServiceById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<?> activate(@PathVariable Long id) {
        medicalTypeServiceService.activateMedicalTypeServiceById(id);
        return ResponseEntity.noContent().build();
    }
}
