package org.gatodev.arcadiaclinica.controller.persons;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.DTO.persons.DoctorDTO;
import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.gatodev.arcadiaclinica.service.medical.IMedicalSpecialtyService;
import org.gatodev.arcadiaclinica.service.persons.IDoctorService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final IDoctorService doctorService;
    private final IMedicalSpecialtyService medicalSpecialtyService;

    public DoctorController(
            IDoctorService doctorService,
            IMedicalSpecialtyService medicalSpecialtyService
    ) {
        this.doctorService = doctorService;
        this.medicalSpecialtyService = medicalSpecialtyService;
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> save(@Valid @RequestBody Doctor doctor) {
        if (doctorService.existDoctor(doctor.getDni(), doctor.getEmail())) {
            throw new DataIntegrityViolationException("El dni y/o email ya existen.");
        }

        MedicalSpecialty ms = medicalSpecialtyService
                .getMedicalSpecialtyById(doctor.getMedicalSpecialty().getId());
        doctor.setMedicalSpecialty(ms);

        return ResponseEntity.ok(doctorService.getDoctorDTO(doctorService.addDoctor(doctor)));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody DoctorDTO doctor) {
        return ResponseEntity.ok(doctorService.getDoctorDTO(doctorService.updateDoctor(doctor)));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(doctorService.getDoctors());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> getByDni(@PathVariable String dni) {
        return ResponseEntity.ok(doctorService.getDoctorByDni(dni));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivate(@PathVariable long id) {
        doctorService.deactivateDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<?> activate(@PathVariable long id) {
        doctorService.activateDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
