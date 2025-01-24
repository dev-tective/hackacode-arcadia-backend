package org.gatodev.arcadiaclinica.service.persons;

import org.gatodev.arcadiaclinica.entity.persons.User;
import org.gatodev.arcadiaclinica.util.enums.Role;
import java.util.List;
import java.util.UUID;

public interface IUserService {
    User getUserByDni(String dni);

    User getUserByEmail(String email);

    User getUserById(UUID id);

    void deleteUserByDni(String dni);

    List<User> getAllUsers();

    List<User> getAllUsersByRole(Role role);

    void verificateExistByDni(String dni);

    void verificateExistByEmail(String email);

    void verificatePassword(User user);

    void checkSaveEntity(User person);

    void checkUpdateEntity(User person, User updatedPerson);

    boolean verifyPassword(User user, String rawPassword);
}
