package org.gatodev.arcadiaclinica.entity.persons;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.gatodev.arcadiaclinica.entity.IFieldsValidate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person implements IFieldsValidate {
    @NotBlank(message = "El dni no puede ser vacío.")
    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @NotBlank(message = "El nombre no puede ser vacío.")
    @Column(nullable = false)
    private String firstname;

    @NotBlank(message = "El apellido no puede ser vacío.")
    @Column(nullable = false)
    private String lastname;

    @NotBlank(message = "El email no puede ser vacío.")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(length = 9)
    private String numberPhone;
    private String address;
    private LocalDate birthDate;
}
