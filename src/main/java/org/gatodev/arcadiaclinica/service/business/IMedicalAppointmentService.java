package org.gatodev.arcadiaclinica.service.business;

import org.gatodev.arcadiaclinica.DTO.business.MedicalAppointmentRequest;
import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import java.time.LocalDate;
import java.util.List;

public interface IMedicalAppointmentService {
    MedicalAppointment addMedicalAppointment(MedicalAppointmentRequest request);
    List<MedicalAppointment> addMedicalAppointment(List<MedicalAppointmentRequest> requests);
    MedicalAppointment updateMedicalAppointment(MedicalAppointment ma);
    MedicalAppointment getMedicalAppointmentById(long id);
    List<MedicalAppointment> getAllMedicalAppointmentsByDni(String dni);
    List<MedicalAppointment> getAllMedicalAppointments();
}
