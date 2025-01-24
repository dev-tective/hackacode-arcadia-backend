package com.arcadia.clinica.controller;


import com.arcadia.clinica.DTOS.business_services.MedicalServicePackageDTO;
import com.arcadia.clinica.entity.business_services.MedicalServicePackage;
import com.arcadia.clinica.response.ResponseMessage;
import com.arcadia.clinica.service.business_services.IMedicalServicePackage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medical-service-package")
public class MedicalServicePackageController {

    private final IMedicalServicePackage medicalServicePackage;
    public MedicalServicePackageController(IMedicalServicePackage medicalServicePackage) {
        this.medicalServicePackage = medicalServicePackage;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addMedicalServicePackage(@Valid @RequestBody MedicalServicePackageDTO medicalServicePackageDto) {
        this.medicalServicePackage.save(medicalServicePackageDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("medical service package added successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MedicalServicePackage>> getMedicalServicePackageById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.medicalServicePackage.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicalServicePackage>> getMedicalServicePackages() {
        return ResponseEntity.status(HttpStatus.OK).body(this.medicalServicePackage.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteMedicalServicePackage(@PathVariable Integer id) {
        this.medicalServicePackage.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("medical service package deleted successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseMessage> updateMedicalServicePackage(@PathVariable Integer id,
                                                                      @Valid @RequestBody MedicalServicePackageDTO medicalServicePackageDto) {
        this.medicalServicePackage.update(id, medicalServicePackageDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("medical service package updated successfully"));
    }

}
