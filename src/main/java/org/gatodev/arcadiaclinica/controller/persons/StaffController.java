package org.gatodev.arcadiaclinica.controller.persons;

import org.gatodev.arcadiaclinica.entity.persons.Staff;
import org.gatodev.arcadiaclinica.service.persons.IStaffService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/staff")
public class StaffController {
    
    private final IStaffService staffService;

    public StaffController(IStaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/add/staff")
    public ResponseEntity<?> addStaff(@RequestBody Staff staff) {
        return ResponseEntity.ok(staffService.addEntity(staff));
    }
}
