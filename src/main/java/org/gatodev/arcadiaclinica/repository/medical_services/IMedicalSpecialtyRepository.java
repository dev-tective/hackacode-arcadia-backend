package org.gatodev.arcadiaclinica.repository.medical_services;

import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicalSpecialtyRepository extends JpaRepository<MedicalSpecialty, Long> {
}
