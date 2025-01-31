package org.gatodev.arcadiaclinica.repository.business;

import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {
}
