package org.gatodev.arcadiaclinica.DTO.medical;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public record MedicalServicePackageRequest(
        @NotNull(message = "El nombre del servicio no puede ser nulo.")
        @NotBlank(message = "El nombre del servicio no puede estar vacío.")
        String name,

        @NotNull(message = "La descripción del servicio no puede ser nulo.")
        @NotBlank(message = "La descripción del servicio no puede estar vacío.")
        String description,

        @NotNull(message = "El código del servicio no puede ser nulo.")
        @NotBlank(message = "El código del servicio no puede estar vacío.")
        String code,

        @Size(min = 2)
        @NotNull(message = "Deben contener servicios medicos.")
        @NotEmpty(message = "La lista de servicios no debe estar vacía.")
        List<Long> idMedicalServices,

        LocalDate available,
        LocalDate notAvailable
) {
    public MedicalServicePackageRequest {
        if (available == null) available = LocalDate.now();
        if (notAvailable == null) notAvailable = LocalDate.now().plusMonths(1);
    }
}
