package org.gatodev.arcadiaclinica.service.persons.impl;

import org.gatodev.arcadiaclinica.entity.persons.User;
import org.gatodev.arcadiaclinica.repository.persons.IUserRepository;
import org.gatodev.arcadiaclinica.service.persons.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void deleteUser(User user) {
        User deleteUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User with dni " + user.getDni() + " not found"));
        deleteUser.setEnabled(false);
        userRepository.save(deleteUser);
    }

    @Override
    public void verificateExistUser(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new RuntimeException("User with id " + user.getId() + " not found");
        }
    }

    @Override
    public void encryptPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public boolean verifyPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
