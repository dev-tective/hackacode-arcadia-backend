package org.gatodev.arcadiaclinica.DTO.medical;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MedicalServiceRequest(
        Long id,

        @NotNull(message = "El ID del tipo de servicio médico no puede ser nulo.")
        Long idMedicalTypeService,

        @NotNull(message = "El ID de la especialidad médica no puede ser nulo.")
        Long idMedicalSpecialty,

        @NotNull(message = "El nombre del servicio no puede ser nulo.")
        @NotBlank(message = "El nombre del servicio no puede estar vacío.")
        String name,

        @NotNull(message = "La descripción del servicio no puede ser nulo.")
        @NotBlank(message = "La descripción del servicio no puede estar vacío.")
        String description,

        @NotNull(message = "El código del servicio no puede ser nulo.")
        @NotBlank(message = "El código del servicio no puede estar vacío.")
        String code,

        @Positive(message = "La duración debe ser un número positivo.")
        Long duration,

        @Positive(message = "El precio debe ser un número positivo.")
        Double price
) {
    public MedicalServiceRequest {
        if (duration == null) duration = 60L;
        if (price == null) price = 40.0;
    }
}
