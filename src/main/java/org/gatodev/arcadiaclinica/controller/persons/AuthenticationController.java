package org.gatodev.arcadiaclinica.controller.persons;

import org.gatodev.arcadiaclinica.DTO.auth.LoginRequest;
import org.gatodev.arcadiaclinica.entity.persons.Staff;
import org.gatodev.arcadiaclinica.service.persons.IStaffService;
import org.gatodev.arcadiaclinica.service.persons.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final IUserService userService;
    private final IStaffService staffService;

    public AuthenticationController(IUserService userService, IStaffService staffService) {
        this.userService = userService;
        this.staffService = staffService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Staff staff = staffService.getEntityByEmail(loginRequest.email());

        if (!staff.getDni().equals(loginRequest.dni())) {
            throw new IllegalArgumentException("Invalid DNI");
        }

        if (!userService.verifyPassword(staff, loginRequest.password())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return ResponseEntity.ok(staff);
    }
}
