package org.gatodev.arcadiaclinica.DTO.business;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MedicalAppointmentRequest(
        LocalDateTime appointmentStart,
        @NotNull(message = "Debe incluir un servicio medico.")
        Long idMedicalService,
        @NotNull(message = "Debe incluir un paciente.")
        Long idPatient,
        @NotNull(message = "Debe incluir un doctor.")
        Long idDoctor
) {
    public MedicalAppointmentRequest {
        if (appointmentStart == null) appointmentStart = LocalDateTime.now();
    }
}
