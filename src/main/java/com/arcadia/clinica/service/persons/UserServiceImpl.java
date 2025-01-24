package com.arcadia.clinica.service.persons;

import com.arcadia.clinica.entity.persons.User;
import com.arcadia.clinica.repository.persons.IUserRepository;
import com.arcadia.clinica.util.CheckEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService, CheckEntity {

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        User newUser = new User();

        if (verificateStringField(user.getDni()))
            throw new IllegalArgumentException("Dni es un campo obligatorio.");
        if (verificateStringField(user.getEmail()))
            throw new IllegalArgumentException("Email es un campo obligatorio.");
        if (verificateStringField(user.getFirstname()))
            throw new IllegalArgumentException("Firstname es un campo obligatorio.");
        if (verificateStringField(user.getLastname()))
            throw new IllegalArgumentException("Lastname es un campo obligatorio.");
        if (user.getRole() == null)
            throw new IllegalArgumentException("Role es un campo obligatorio.");
        if (user.getEntranceWork() == null || user.getExitWork() == null)
            throw new IllegalArgumentException("Debe indicar la entrada y salida laboral.");

        newUser.setDni(user.getDni());
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstname());
        newUser.setLastName(user.getLastname());
        newUser.setRole(user.getRole());
        newUser.setEntranceWork(user.getEntranceWork());
        newUser.setExitWork(user.getExitWork());
        newUser.setAddress(user.getAddress());
        newUser.setNumberPhone(user.getNumberPhone());
        newUser.setDayOff(user.getDayOff());
        newUser.setSalary(user.getSalary());
        newUser.setBirthDate(user.getBirthDate());

        if (user.getPassword() != null) newUser.setPassword(user.getPassword());
        else newUser.setPassword(user.getDni());

        return userRepository.save(newUser);
    }

    @Override
    public User getUserByDni(String dni) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(String dni) {

    }

    public static void verificateUser(User user) {

    }
}
