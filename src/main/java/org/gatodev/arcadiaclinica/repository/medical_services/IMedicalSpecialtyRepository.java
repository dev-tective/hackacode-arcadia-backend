package org.gatodev.arcadiaclinica.repository.medical_services;

import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IMedicalSpecialtyRepository extends JpaRepository<MedicalSpecialty, Long> {
    boolean existsByName(String name);
    Optional<MedicalSpecialty> findByIdOrName(Long id, String name);
}
