package org.gatodev.arcadiaclinica.controller.persons;

import org.gatodev.arcadiaclinica.DTO.auth.LoginRequest;
import org.gatodev.arcadiaclinica.entity.persons.User;
import org.gatodev.arcadiaclinica.service.persons.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final IUserService userService;

    public AuthenticationController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.getUserByEmail(loginRequest.email());

        if (!user.getEnabled()) {
            return ResponseEntity.status(403).build();
        }

        if (userService.verifyPassword(user, loginRequest.password())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return ResponseEntity.ok(user);
    }
}
