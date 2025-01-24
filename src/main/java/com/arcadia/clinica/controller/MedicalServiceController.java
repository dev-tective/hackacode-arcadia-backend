package com.arcadia.clinica.controller;

import com.arcadia.clinica.DTOS.business_services.MedicalServiceDTO;
import com.arcadia.clinica.entity.business_services.MedicalService;
import com.arcadia.clinica.response.ResponseMessage;
import com.arcadia.clinica.service.business_services.IMedicalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medical-service")
public class MedicalServiceController {

    private final IMedicalService medicalService;
    public MedicalServiceController(IMedicalService medicalService) {
        this.medicalService = medicalService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> add(@RequestBody MedicalServiceDTO medicalServiceDto) {
        this.medicalService.save(medicalServiceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("medical service added successfully"));
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<MedicalService>> getMedicalServiceById(@RequestParam("id") Integer id) {
        this.medicalService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(this.medicalService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicalService>> getAllMedicalServices() {
        return ResponseEntity.status(HttpStatus.OK).body(this.medicalService.findAll());
    }

    @DeleteMapping("/delete/id")
    public ResponseEntity<ResponseMessage> deleteMedicalServiceById(@RequestParam("id") Integer id) {
        this.medicalService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseMessage("medical service deleted successfully"));
    }

}
