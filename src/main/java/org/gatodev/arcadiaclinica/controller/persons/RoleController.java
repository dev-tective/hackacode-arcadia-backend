package org.gatodev.arcadiaclinica.controller.persons;

import org.gatodev.arcadiaclinica.entity.persons.Role;
import org.gatodev.arcadiaclinica.service.persons.IRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.addRole(role));
    }
}
