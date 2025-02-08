package org.gatodev.arcadiaclinica.DTO.business;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record ReceiptRequest(
        Long id,

        LocalDateTime attentionDate,

        Long idClient,

        @NotNull
        Long idPatient,

        @NotNull @Size(min = 1)
        List<MedicalAppointmentRequest> medicalAppointments
) {
    public ReceiptRequest {
        if (attentionDate == null) attentionDate = LocalDateTime.now();
    }
}
