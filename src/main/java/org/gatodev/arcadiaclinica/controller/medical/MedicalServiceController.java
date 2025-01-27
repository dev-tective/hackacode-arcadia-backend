package org.gatodev.arcadiaclinica.controller.medical;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.service.medical_services.IMedicalServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical-service")
public class MedicalServiceController {

    private final IMedicalServiceService medicalServiceService;

    public MedicalServiceController(IMedicalServiceService medicalServiceService) {
        this.medicalServiceService = medicalServiceService;
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody MedicalService medicalService) {
        MedicalService ms = medicalServiceService.addMedicalService(medicalService);
        return ResponseEntity.ok(medicalServiceService.convertToDTO(ms));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(medicalServiceService.getAllMedicalService()
                .stream()
                .map(medicalServiceService::convertToWPDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        MedicalService ms = medicalServiceService.getMedicalServiceById(id);
        return ResponseEntity.ok(medicalServiceService.convertToWPDTO(ms));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody MedicalService medicalService) {
        MedicalService ms = medicalServiceService.updateMedicalService(medicalService);
        return ResponseEntity.ok(medicalServiceService.convertToWPDTO(ms));
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
