package org.gatodev.arcadiaclinica.repository.persons;

import org.gatodev.arcadiaclinica.entity.persons.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByDni(String dni);

    boolean existsByDniOrEmail(String dni, String email);
}
