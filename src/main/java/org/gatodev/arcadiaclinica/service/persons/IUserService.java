package org.gatodev.arcadiaclinica.service.persons;

import org.gatodev.arcadiaclinica.entity.persons.User;

public interface IUserService {

    void deleteUser(User user);

    void verificateExistUser(User user);

    void encryptPassword(User user);

    boolean verifyPassword(User user, String rawPassword);
}
