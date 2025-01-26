package org.gatodev.arcadiaclinica.service.persons;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.entity.persons.User;
import org.mindrot.jbcrypt.BCrypt;

public interface IUserService {

    void deleteUser(User user);


    void verificateExistUser(User user);

    void encryptPassword(User user);

    boolean verifyPassword(User user, String rawPassword);
}
