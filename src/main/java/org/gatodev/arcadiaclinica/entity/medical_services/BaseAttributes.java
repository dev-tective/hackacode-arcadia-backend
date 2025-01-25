package org.gatodev.arcadiaclinica.entity.medical_services;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseAttributes {
    @NotBlank(message = "El código no puede ser vacío.")
    @Column(nullable = false, unique = true)
    private String code;

    @NotBlank(message = "El nombre no puede ser vacío.")
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean state = true;

    public void validateCode() {
        if (code.length() != 3) {
            throw new RuntimeException("Medical type code should be 3 digits");
        }
    }
}
