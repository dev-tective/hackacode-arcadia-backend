package org.gatodev.arcadiaclinica.controller.medical;

import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.gatodev.arcadiaclinica.service.medical.IMedicalSpecialtyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/medical-specialty")
public class MedicalSpecialtyController {

    private final IMedicalSpecialtyService medicalSpecialtyService;

    public MedicalSpecialtyController(IMedicalSpecialtyService medicalSpecialtyService) {
        this.medicalSpecialtyService = medicalSpecialtyService;
    }

    @GetMapping
    public ResponseEntity<List<MedicalSpecialty>> getMedicalSpecialties() {
        return ResponseEntity.ok(medicalSpecialtyService.getAllMedicalSpecialties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalSpecialty> getMedicalSpecialtyById(@PathVariable long id) {
        return ResponseEntity.ok(medicalSpecialtyService.getMedicalSpecialtyById(id));
    }

    @PostMapping
    public ResponseEntity<MedicalSpecialty> addMedicalSpecialty(@RequestBody MedicalSpecialty ms) {
        return ResponseEntity.ok(medicalSpecialtyService.addMedicalSpecialty(ms));
    }

    @PutMapping
    public ResponseEntity<MedicalSpecialty> updateMedicalSpecialty(@RequestBody MedicalSpecialty ms) {
        return ResponseEntity.ok(medicalSpecialtyService.updateMedicalSpecialty(ms));
    }
}
