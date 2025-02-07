package org.gatodev.arcadiaclinica.controller.medical;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.entity.medical.MedicalTypeService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalTypeServiceS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/medical-type-service")
public class MedicalTypeServiceController {

    private final IMedicalTypeServiceS medicalTypeServiceS;

    public MedicalTypeServiceController(IMedicalTypeServiceS medicalTypeServiceS) {
        this.medicalTypeServiceS = medicalTypeServiceS;
    }

    @GetMapping
    public ResponseEntity<List<MedicalTypeService>> getAllMedicalTypeServices() {
        return ResponseEntity.ok(medicalTypeServiceS.getAllMedicalTypeServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalTypeService> getMedicalTypeServiceById(@PathVariable long id) {
        return ResponseEntity.ok(medicalTypeServiceS.getMedicalTypeServiceById(id));
    }

    @PostMapping()
    public ResponseEntity<MedicalTypeService> addMedicalTypeService(@Valid @RequestBody MedicalTypeService mts) {
        return ResponseEntity.ok(medicalTypeServiceS.addMedicalTypeService(mts));
    }

    @PutMapping
    public ResponseEntity<MedicalTypeService> updateMedicalTypeService(@Valid @RequestBody MedicalTypeService mts) {
        return ResponseEntity.ok(medicalTypeServiceS.updateMedicalTypeService(mts));
    }
}
