package org.gatodev.arcadiaclinica.entity.persons;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.regex.Pattern;

@Getter
@Setter
@MappedSuperclass
public class Person {
    @NotNull @NotBlank
    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @NotNull @NotBlank
    @Column(nullable = false, length = 30)
    private String firstname;

    @NotNull @NotBlank
    @Column(nullable = false, length = 30)
    private String lastname;

    @NotNull @NotBlank
    @Column(unique = true, length = 30)
    private String email;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(length = 9)
    private String numberPhone;

    @Column(length = 50)
    private String address;

    private LocalDate birthDate;

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public static boolean isValidDni(String dni) {
        if (dni.length() != 8) return false;
        try {
            Long.parseLong(dni);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidNumberPhone(String numberPhone) {
        if (numberPhone.length() != 9) return false;
        if (numberPhone.charAt(0) != '9') return false;
        try {
            Long.parseLong(numberPhone);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void isEnabled() {
        if (!enabled) {
            throw new RuntimeException("Person not enabled");
        }
    }

    public void validateFields(String email, String dni, String numberPhone) {
        if (email != null && !Person.isValidEmail(email)) {
            throw new IllegalArgumentException("El email es invalido.");
        }
        if (!Person.isValidDni(dni)) {
            throw new IllegalArgumentException("El dni es invalido.");
        }
        if (numberPhone != null && !Person.isValidNumberPhone(numberPhone)) {
            throw new IllegalArgumentException("El numero de teléfono es invalido.");
        }
    }
}
