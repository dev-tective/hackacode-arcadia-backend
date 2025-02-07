package org.gatodev.arcadiaclinica.repository.medical;

import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicalSpecialtyRepository extends JpaRepository<MedicalSpecialty, Long> {
}
