package org.gatodev.arcadiaclinica.controller.persons;

import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.gatodev.arcadiaclinica.service.persons.IDoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Implementar los DTOS para no enviar entidades infinitas
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final IDoctorService doctorService;

    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.addEntity(doctor));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.updateEntity(doctor));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(doctorService.getAllEntities());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(doctorService.getEntityById(id));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> getByDni(@PathVariable String dni) {
        return ResponseEntity.ok(doctorService.getEntityByDni(dni));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivate(@PathVariable long id) {
        doctorService.deactivateEntity(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<?> activate(@PathVariable long id) {
        doctorService.activateEntity(id);
        return ResponseEntity.noContent().build();
    }
}
