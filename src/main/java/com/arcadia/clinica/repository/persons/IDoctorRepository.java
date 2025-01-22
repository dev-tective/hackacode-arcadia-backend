package com.arcadia.clinica.repository.persons;

import com.arcadia.clinica.entity.persons.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, UUID> {
}