package org.gatodev.arcadiaclinica.repository.persons;

import java.util.Optional;
import java.util.UUID;
import org.gatodev.arcadiaclinica.entity.persons.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, UUID> {
    boolean existsByDni(String dni);

    boolean existsByEmail(String email);

    Optional<User> findByDni(String dni);

    Optional<User> findByEmail(String email);
}
