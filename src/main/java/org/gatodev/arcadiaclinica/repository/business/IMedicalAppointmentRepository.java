package org.gatodev.arcadiaclinica.repository.business;

import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IMedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {
    List<MedicalAppointment> findAllByPatient_Dni(String dni);
    boolean existsByDoctor_IdAndAppointmentStartBeforeAndAppointmentEndAfter(
            Long doctorId, LocalDateTime end, LocalDateTime start
    );
}
