package org.gatodev.arcadiaclinica.service.persons.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.DTO.auth.UpdateRequest;
import org.gatodev.arcadiaclinica.entity.persons.Role;
import org.gatodev.arcadiaclinica.entity.persons.User;
import org.gatodev.arcadiaclinica.repository.persons.IUserRepository;
import org.gatodev.arcadiaclinica.service.persons.IRoleService;
import org.gatodev.arcadiaclinica.service.persons.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleService roleService;

    public UserServiceImpl(
            IUserRepository userRepository,
            PasswordEncoder passwordEncoder,
            IRoleService roleService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User addUser(User user) {
        if (user.isValidEmail(user.getEmail())) {
            throw new RuntimeException("Invalid email");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getRoleByName("ROLE_USER"));
        if (user.getIsAdmin()) {
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User updateUserEmail(UUID id, UpdateRequest request) {
        if (request.oldField().equals(request.newField())) {
            throw new IllegalArgumentException("Emails cannot be the same");
        }
        if (request.isValidEmail(request.oldField()) && request.isValidEmail(request.newField())) {
            throw new RuntimeException("Invalid email");
        }
        User user = getUserById(id);
        if (!request.oldField().equals(user.getEmail())){
            throw new IllegalArgumentException("Emails not match");
        }
        user.setEmail(request.newField());
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User updateUserPassword(UUID id, UpdateRequest request) {
        User user = getUserById(id);
        if (verifyPassword(user, request.oldField())) {
            throw new RuntimeException("Passwords do not match");
        }
        user.setPassword(passwordEncoder.encode(request.newField()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void deactivateUser(UUID id) {
        User deleteUser = getUserById(id);
        deleteUser.setEnabled(false);
    }

    @Transactional
    @Override
    public void activateUser(UUID id) {
        User activateUser = getUserById(id);
        activateUser.setEnabled(true);
    }

    @Override
    public void verificateExistUserByEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new EntityNotFoundException("User with email " + email + " not found");
        }
    }

    @Override
    public boolean verifyPassword(User user, String rawPassword) {
        return !passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
