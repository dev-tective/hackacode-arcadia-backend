package org.gatodev.arcadiaclinica.service.business;

import org.gatodev.arcadiaclinica.DTO.business.MedicalAppointmentRequest;
import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import org.gatodev.arcadiaclinica.entity.persons.Client;

public interface IMedicalAppointmentService {
    void addMedicalAppointment(MedicalAppointment medicalAppointment);

    MedicalAppointment createMedicalAppointment(MedicalAppointmentRequest request, Client patient);
}
