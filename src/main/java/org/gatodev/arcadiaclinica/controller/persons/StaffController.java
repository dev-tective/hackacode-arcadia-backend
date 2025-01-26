package org.gatodev.arcadiaclinica.controller.persons;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.entity.persons.Staff;
import org.gatodev.arcadiaclinica.service.persons.IStaffService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/staff")
@Validated
public class StaffController {
    
    private final IStaffService staffService;

    public StaffController(IStaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addStaff(@Valid @RequestBody Staff staff) {
        return ResponseEntity.status(HttpStatus.CREATED).body(staffService.addEntity(staff));
    }

    @GetMapping
    public ResponseEntity<?> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllEntities());
    }
}
