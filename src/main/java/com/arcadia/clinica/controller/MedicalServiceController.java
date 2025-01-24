package com.arcadia.clinica.controller;

import com.arcadia.clinica.DTOS.business_services.MedicalServiceDTO;
import com.arcadia.clinica.entity.business_services.MedicalService;
import com.arcadia.clinica.response.ResponseMessage;
import com.arcadia.clinica.service.business_services.IMedicalService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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
    public ResponseEntity<ResponseMessage> add(@Valid @RequestBody MedicalServiceDTO medicalServiceDto) {
        this.medicalService.save(medicalServiceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("medical service added successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MedicalService>> getMedicalServiceById(@PathVariable("id") Integer id) {
        this.medicalService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(this.medicalService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicalService>> getAllMedicalServices() {
        return ResponseEntity.status(HttpStatus.OK).body(this.medicalService.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteMedicalServiceById(@PathVariable("id") Integer id) {
        this.medicalService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage("medical service deleted successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseMessage> updateMedicalService(@PathVariable(name = "id")Integer id,
                                                                @Valid @RequestBody MedicalServiceDTO medicalServiceDto) {
        this.medicalService.update(id, medicalServiceDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage("medical service updated successfully"));
    }

}
