package org.gatodev.arcadiaclinica.controller.persons;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.DTO.auth.UpdateRequest;
import org.gatodev.arcadiaclinica.entity.persons.User;
import org.gatodev.arcadiaclinica.service.persons.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PutMapping("/{id}/update/password")
    public ResponseEntity<User> updatePassword(
            @PathVariable UUID id, @Valid UpdateRequest request
    ) {
        return ResponseEntity.ok(userService.updateUserPassword(id, request));
    }

    @PutMapping("/{id}/update/email")
    public ResponseEntity<User> updateEmail(
            @PathVariable UUID id, @Valid UpdateRequest request
    ) {
        return ResponseEntity.ok(userService.updateUserPassword(id, request));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivate(@PathVariable UUID id) {
        userService.deactivateUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}/activate")
    public ResponseEntity<?> activate(@PathVariable UUID id) {
        userService.activateUser(id);
        return ResponseEntity.noContent().build();
    }
}
