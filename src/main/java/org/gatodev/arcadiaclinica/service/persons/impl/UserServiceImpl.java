package org.gatodev.arcadiaclinica.service.persons.impl;

import org.gatodev.arcadiaclinica.entity.persons.User;
import org.gatodev.arcadiaclinica.repository.persons.IUserRepository;
import org.gatodev.arcadiaclinica.service.persons.IPersonService;
import org.gatodev.arcadiaclinica.service.persons.IUserService;
import org.gatodev.arcadiaclinica.util.enums.Role;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService, IPersonService<User> {

    private final IUserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByDni(String dni) {
        return userRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with email " + email + " not found"));
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    @Transactional
    @Override
    public void deleteUserByDni(String dni) {
        verificateExistByDni(dni);
        getUserByDni(dni).setActive(false);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsersByRole(Role role) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == role)
                .toList();
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
    public void verificatePassword(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(BCrypt.hashpw(user.getDni(), BCrypt.gensalt()));
            return;
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
    }

    // Revisa campos vacíos y existencias antes de guardar y actualizar
    @Override
    public void checkSaveEntity(User person) {
        verificateExistByDni(person.getDni());
        verificateExistByEmail(person.getEmail());
        checkSavePerson(person);
        verificatePassword(person);
    }

    @Override
    public void checkUpdateEntity(User person, User updatedPerson) {
        verificateExistByEmail(person.getEmail());
        verificateExistByDni(person.getDni());
        checkUpdatePerson(person, updatedPerson);
    }

    @Override
    public boolean verifyPassword(User user, String rawPassword) {
        return BCrypt.checkpw(rawPassword, user.getPassword());
    }
}
