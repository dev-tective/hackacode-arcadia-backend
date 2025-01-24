package com.arcadia.clinica.service.persons;

import com.arcadia.clinica.entity.persons.User;
import java.util.List;

public interface IUserService {
    User addUser(User user);
    User getUserByDni(String dni);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(String dni);
}
