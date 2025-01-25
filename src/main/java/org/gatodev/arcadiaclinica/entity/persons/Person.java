package org.gatodev.arcadiaclinica.entity.persons;

import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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

    @Column(length = 9)
    private String numberPhone;
    private String address;
    private LocalDate birthDate;

    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return !pattern.matcher(email).matches();
    }

    public boolean isValidDni(String dni) {
        if (dni.length() != 8) return false;

        try {
            Long.parseLong(dni);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public boolean isValidNumberPhone(String numberPhone) {
        if (numberPhone.length() != 9) return false;
        if (numberPhone.charAt(0) != '9') return false;

        try {
            Long.parseLong(numberPhone);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
