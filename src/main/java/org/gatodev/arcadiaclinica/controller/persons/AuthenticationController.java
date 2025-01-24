package org.gatodev.arcadiaclinica.controller.persons;

import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.gatodev.arcadiaclinica.entity.persons.Staff;
import org.gatodev.arcadiaclinica.entity.persons.User;
import org.gatodev.arcadiaclinica.service.persons.IDoctorService;
import org.gatodev.arcadiaclinica.service.persons.IStaffService;
import org.gatodev.arcadiaclinica.service.persons.IUserService;
import org.gatodev.arcadiaclinica.util.enums.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final IUserService userService;
    private final IStaffService staffService;
    private final IDoctorService doctorService;

    public AuthenticationController(IStaffService staffService, IDoctorService doctorService, IUserService userService) {
        this.staffService = staffService;
        this.doctorService = doctorService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String dni, @RequestParam String email, @RequestParam String password) {
        User user = userService.getUserByDni(dni);

        if (user.getRole().equals(Role.CLIENT) || user.getRole().equals(Role.DOCTOR))
            throw new IllegalArgumentException("Role without access");

        if (!userService.verifyPassword(user, password)) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> addStaff(@RequestBody Staff staff) {
        return ResponseEntity.ok(staffService.addStaff(staff));
    }

    @PostMapping("/register/doctor")
    public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }
}
