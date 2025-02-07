package org.gatodev.arcadiaclinica.repository.medical;

import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicalServiceRepository extends JpaRepository<MedicalService, Long> {
}
