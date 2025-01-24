package org.gatodev.arcadiaclinica.repository.persons;

import java.util.Optional;
import java.util.UUID;
import org.gatodev.arcadiaclinica.entity.persons.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStaffRepository extends JpaRepository<Staff, UUID> {
    Optional<Staff> findByDni(String dni);

    Optional<Staff> findByEmail(String email);
}
