package org.gatodev.arcadiaclinica.service.persons.impl;

import org.gatodev.arcadiaclinica.entity.persons.User;
import org.gatodev.arcadiaclinica.repository.persons.IUserRepository;
import org.gatodev.arcadiaclinica.service.persons.IUserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void deleteUserByDni(String dni) {
        verificateExistByDni(dni);
        User deleteUser = userRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("User with " + dni + " not found"));
        deleteUser.setActive(false);
    }

    @Override
    public void verificateExistByDni(String dni) {
        if (userRepository.existsByDni(dni)) {
            throw new IllegalArgumentException("Dni " + dni + " already exists");
        }
    }

    @Override
    public void verificateExistByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email " + email + " already exists");
        }
    }

    @Override
    public boolean verifyPassword(User user, String rawPassword) {
        return BCrypt.checkpw(rawPassword, user.getPassword());
    }
}
