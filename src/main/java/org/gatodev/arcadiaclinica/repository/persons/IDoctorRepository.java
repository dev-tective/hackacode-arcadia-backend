package org.gatodev.arcadiaclinica.repository.persons;

import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByDni(String dni);

    boolean existsByDniOrEmail(String attributesDni, String attributesEmail);
}
