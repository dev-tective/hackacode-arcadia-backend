package org.gatodev.arcadiaclinica.controller.medical;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.DTO.medical.MedicalServicePackageRequest;
import org.gatodev.arcadiaclinica.entity.medical.MedicalAttributes;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.medical.MedicalServicePackage;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServicePackageService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServiceS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medical-service-package")
public class MedicalServicePackageController {

    private final IMedicalServicePackageService medicalServicePackageService;
    private final IMedicalServiceS medicalServiceS;

    public MedicalServicePackageController(
            IMedicalServicePackageService medicalServicePackageService,
            IMedicalServiceS medicalServiceS
    ) {
        this.medicalServicePackageService = medicalServicePackageService;
        this.medicalServiceS = medicalServiceS;
    }

    @GetMapping
    public ResponseEntity<List<MedicalServicePackage>> getMedicalServicePackages() {
        return ResponseEntity.ok(medicalServicePackageService.getAllMedicalServicePackages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalServicePackage> getMedicalServicePackageById(@PathVariable long id) {
        return ResponseEntity.ok(medicalServicePackageService.getMedicalServicePackageById(id));
    }

    @PostMapping
    public ResponseEntity<MedicalServicePackage> addMedicalServicePackage(
            @Valid @RequestBody MedicalServicePackageRequest request
    ) {
        if (request.idMedicalServices().size() != new HashSet<>(request.idMedicalServices()).size()) {
            return ResponseEntity.badRequest().build();
        }
        MedicalServicePackage msp = new MedicalServicePackage();
        return ResponseEntity.ok(medicalServicePackageService
                .addMedicalServicePackage(getMedicalServicePackageEntity(request, msp)));
    }

    @PutMapping
    public ResponseEntity<MedicalServicePackage> updateMedicalServicePackage(
            @Valid @RequestBody MedicalServicePackageRequest request
    ) {
        if (request.id() == null) {
            return ResponseEntity.badRequest().build();
        }

        if (request.idMedicalServices().size() != new HashSet<>(request.idMedicalServices()).size()) {
            return ResponseEntity.badRequest().build();
        }

        MedicalServicePackage msp = medicalServicePackageService.getMedicalServicePackageById(request.id());

        return ResponseEntity.ok(medicalServicePackageService
                .updateMedicalServicePackage(getMedicalServicePackageEntity(request, msp)));
    }

    private MedicalServicePackage getMedicalServicePackageEntity(
            MedicalServicePackageRequest request, MedicalServicePackage medicalServicePackage
    ) {
        List<MedicalService> medicalServices = request.idMedicalServices().stream()
                .map(medicalServiceS::getMedicalServiceById)
                .collect(Collectors.toList());
        medicalServicePackage.setMedicalServices((medicalServices));
        medicalServicePackage.setCode(request.code());
        medicalServicePackage.setName(request.name());
        medicalServicePackage.setDescription(request.description());
        medicalServicePackage.setAvailable(request.available());
        medicalServicePackage.setNotAvailable(request.notAvailable());
        medicalServicePackage.setDuration(medicalServices.stream()
                .map(MedicalAttributes::getDuration)
                .reduce(Duration.ZERO, Duration::plus));
        medicalServicePackage.setPrice(medicalServices.stream()
                .map(MedicalAttributes::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        return medicalServicePackage;
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateMedicalServicePackage(@PathVariable long id) {
        medicalServicePackageService.deactivateMedicalServicePackage(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<?> activateMedicalServicePackage(@PathVariable long id) {
        medicalServicePackageService.activateMedicalServicePackage(id);
        return ResponseEntity.noContent().build();
    }
}
