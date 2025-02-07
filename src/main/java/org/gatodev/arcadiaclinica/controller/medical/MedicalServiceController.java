package org.gatodev.arcadiaclinica.controller.medical;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.DTO.medical.MedicalServiceRequest;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.gatodev.arcadiaclinica.entity.medical.MedicalTypeService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServiceS;
import org.gatodev.arcadiaclinica.service.medical.IMedicalSpecialtyService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalTypeServiceS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/medical-service")
public class MedicalServiceController {

    private final IMedicalServiceS medicalServiceS;
    private final IMedicalSpecialtyService medicalSpecialtyService;
    private final IMedicalTypeServiceS medicalTypeServiceS;

    public MedicalServiceController(
            IMedicalServiceS medicalServiceS,
            IMedicalSpecialtyService medicalSpecialtyService,
            IMedicalTypeServiceS medicalTypeServiceS
    ) {
        this.medicalServiceS = medicalServiceS;
        this.medicalSpecialtyService = medicalSpecialtyService;
        this.medicalTypeServiceS = medicalTypeServiceS;
    }

    @GetMapping
    public ResponseEntity<List<MedicalService>> getAllMedicalServices() {
        return ResponseEntity.ok(medicalServiceS.getAllMedicalServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalService> getMedicalServiceById(@PathVariable long id) {
        return ResponseEntity.ok(medicalServiceS.getMedicalServiceById(id));
    }

    @PostMapping
    public ResponseEntity<MedicalService> addMedicalService(@Valid @RequestBody MedicalServiceRequest request) {
        MedicalService medicalService = new MedicalService();
        return ResponseEntity.ok(getMedicalServiceEntity(request, medicalService));
    }

    @PutMapping
    public ResponseEntity<MedicalService> updateMedicalService(@Valid @RequestBody MedicalServiceRequest request) {
        if (request.id() == null) {
            return ResponseEntity.badRequest().build();
        }
        MedicalService medicalService = medicalServiceS.getMedicalServiceById(request.id());
        return ResponseEntity.ok(getMedicalServiceEntity(request, medicalService));
    }

    private MedicalService getMedicalServiceEntity(
            MedicalServiceRequest request, MedicalService medicalService
    ) {
        MedicalSpecialty ms = medicalSpecialtyService
                .getMedicalSpecialtyById(request.idMedicalSpecialty());

        MedicalTypeService mts = medicalTypeServiceS
                .getMedicalTypeServiceById(request.idMedicalTypeService());
        medicalService.setMedicalSpecialty(ms);
        medicalService.setMedicalTypeService(mts);
        medicalService.setCode(request.code());
        medicalService.setDescription(request.description());
        medicalService.setPrice(BigDecimal.valueOf(request.price()));
        medicalService.setDuration(Duration.ofMinutes(request.duration()));
        medicalService.setName(request.name());
        return medicalServiceS.addMedicalService(medicalService);
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateMedicalService(@PathVariable long id) {
        medicalServiceS.deactivateMedicalService(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<?> activateMedicalService(@PathVariable long id) {
        medicalServiceS.activateMedicalService(id);
        return ResponseEntity.noContent().build();
    }
}
