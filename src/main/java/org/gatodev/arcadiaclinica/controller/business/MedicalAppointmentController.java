package org.gatodev.arcadiaclinica.controller.business;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.DTO.business.MedicalAppointmentRequest;
import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import org.gatodev.arcadiaclinica.service.business.IMedicalAppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/medical-appointment")
public class MedicalAppointmentController {

    private final IMedicalAppointmentService medicalAppointmentService;

    public MedicalAppointmentController(IMedicalAppointmentService medicalAppointmentService) {
        this.medicalAppointmentService = medicalAppointmentService;
    }

    @PostMapping
    public ResponseEntity<MedicalAppointment> create(
            @Valid @RequestBody MedicalAppointmentRequest request
    ) {
        return ResponseEntity.ok(medicalAppointmentService.addMedicalAppointment(request));
    }

    @PostMapping("/package")
    public ResponseEntity<List<MedicalAppointment>> createPerPackage(
            @Valid @RequestBody List<MedicalAppointmentRequest> request
    ) {
        return ResponseEntity.ok(medicalAppointmentService.addMedicalAppointment(request));
    }

    @PutMapping
    public ResponseEntity<MedicalAppointment> update(@Valid @RequestBody MedicalAppointment ma) {
        return ResponseEntity.ok(medicalAppointmentService.updateMedicalAppointment(ma));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalAppointment> get(@PathVariable long id) {
        return ResponseEntity.ok(medicalAppointmentService.getMedicalAppointmentById(id));
    }

    @GetMapping("/all/dni/{dni}/")
    public ResponseEntity<List<MedicalAppointment>> getAllByDni(@PathVariable String dni) {
        return ResponseEntity.ok(medicalAppointmentService.getAllMedicalAppointmentsByDni(dni));
    }
}
