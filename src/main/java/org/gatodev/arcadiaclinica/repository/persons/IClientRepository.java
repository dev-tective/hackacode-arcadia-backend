package org.gatodev.arcadiaclinica.repository.persons;

import org.gatodev.arcadiaclinica.entity.persons.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByDni(String dni);

    Optional<Client> findByEmail(String email);

    List<Client> findAllByFirstname(String firstname);

    List<Client> findAllByLastname(String lastname);

    List<Client> findAllByFirstnameAndLastname(String firstname, String lastname);
}
