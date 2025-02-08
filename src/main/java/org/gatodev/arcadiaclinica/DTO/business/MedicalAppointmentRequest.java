package org.gatodev.arcadiaclinica.DTO.business;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record MedicalAppointmentRequest(
        Long id,

        @NotNull
        LocalDateTime appointmentStart,

        @NotNull
        Long idMedicalService,

        @NotNull
        Long idDoctor
) {
}
