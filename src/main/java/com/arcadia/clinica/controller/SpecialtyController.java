package com.arcadia.clinica.controller;

import com.arcadia.clinica.entity.business_services.Specialty;
import com.arcadia.clinica.response.ResponseMessage;
import com.arcadia.clinica.service.business_services.ISpecialtyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/specialty")
public class SpecialtyController {

    private final ISpecialtyService specialtyService;
    public SpecialtyController(ISpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createSpecialty(@RequestBody Specialty specialty) {
        this.specialtyService.save(specialty);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("specialty created successfully"));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Optional<Specialty>> getSpecialty(@PathVariable(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.specialtyService.findSpecialtyByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Specialty>> getAllSpecialty() {
        List<Specialty> specialtyList = this.specialtyService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(specialtyList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteSpecialty(@PathVariable(value = "id") Integer id) {
        this.specialtyService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage("specialty deleted successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseMessage> updateSpecialty(@PathVariable(value="id") Integer id,
                                                           @RequestBody Specialty specialty) {
        this.specialtyService.update(specialty, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage("specialty updated successfully"));
    }

}
