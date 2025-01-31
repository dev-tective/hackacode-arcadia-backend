package org.gatodev.arcadiaclinica.service.persons;

import org.gatodev.arcadiaclinica.DTO.auth.UpdateRequest;
import org.gatodev.arcadiaclinica.entity.persons.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    User addUser(User user);

    User updateUserEmail(UUID id, UpdateRequest request);

    User updateUserPassword(UUID id, UpdateRequest request);

    User getUserById(UUID id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    void deactivateUser(UUID id);

    void activateUser(UUID id);

    void verificateExistUserByEmail(String email);

    boolean verifyPassword(User user, String rawPassword);
}
