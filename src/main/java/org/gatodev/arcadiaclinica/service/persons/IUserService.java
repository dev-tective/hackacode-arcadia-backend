package org.gatodev.arcadiaclinica.service.persons;

import org.gatodev.arcadiaclinica.entity.persons.User;
import java.util.UUID;

public interface IUserService {
    User addUser(User user);

    User updateUser(User user);

    User getUserById(UUID id);

    User getUserByEmail(String email);

    void deactivateUser(User user);

    void verificateExistUserByEmail(String email);

    void encryptPassword(User user);

    boolean verifyPassword(User user, String rawPassword);
}
