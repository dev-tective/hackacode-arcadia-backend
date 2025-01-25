package org.gatodev.arcadiaclinica.service.persons;


import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.entity.persons.User;
import org.mindrot.jbcrypt.BCrypt;

public interface IUserService {

    void deleteUserByDni(String dni);

    void verificateExistByDni(String dni);

    void verificateExistByEmail(String email);

    static void encryptPassword(@Valid User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
    }

    boolean verifyPassword(User user, String rawPassword);
}
