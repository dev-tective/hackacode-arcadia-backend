package org.gatodev.arcadiaclinica.controller.medical;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.DTO.medical.MedicalServiceRequest;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServiceService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalSpecialtyService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalTypeServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical-service")
public class MedicalServiceController {

    private final IMedicalServiceService medicalServiceService;
    private final IMedicalTypeServiceService medicalTypeServiceService;
    private final IMedicalSpecialtyService medicalSpecialtyService;

    public MedicalServiceController(
            IMedicalServiceService medicalServiceService,
            IMedicalTypeServiceService medicalTypeServiceService,
            IMedicalSpecialtyService medicalSpecialtyService
    ) {
        this.medicalServiceService = medicalServiceService;
        this.medicalTypeServiceService = medicalTypeServiceService;
        this.medicalSpecialtyService = medicalSpecialtyService;
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody MedicalServiceRequest request) {
        return ResponseEntity.ok(medicalServiceService.addMedicalService(, ));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(medicalServiceService.getAllMedicalService());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(medicalServiceService.getMedicalServiceById(id));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody MedicalService medicalService) {
        return ResponseEntity.ok(medicalServiceService.updateMedicalService(medicalService));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivate(@PathVariable long id) {
        medicalServiceService.deactivateMedicalServiceById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<?> activate(@PathVariable long id) {
        medicalServiceService.activateMedicalServiceById(id);
        return ResponseEntity.noContent().build();
    }
}
