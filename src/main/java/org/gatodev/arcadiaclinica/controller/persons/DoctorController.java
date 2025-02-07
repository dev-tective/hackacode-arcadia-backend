package org.gatodev.arcadiaclinica.controller.persons;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.gatodev.arcadiaclinica.service.persons.IDoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final IDoctorService doctorService;

    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }

    @PutMapping
    public ResponseEntity<?> updateDoctor(@Valid @RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.updateDoctor(doctor));
    }

    @GetMapping
    public ResponseEntity<?> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> getDoctorByDni(@PathVariable String dni) {
        return ResponseEntity.ok(doctorService.getDoctorByDni(dni));
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateDoctor(@PathVariable long id) {
        doctorService.deactivateDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<?> activateDoctor(@PathVariable long id) {
        doctorService.activateDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
