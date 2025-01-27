package org.gatodev.arcadiaclinica.repository.medical_services;

import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicalServiceRepository extends JpaRepository<MedicalService, Long> {
}
