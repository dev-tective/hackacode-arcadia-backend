package org.gatodev.arcadiaclinica.DTO.persons;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PersonDTO {
    private UUID id;
    private String dni;
    private String firstname;
    private String lastname;
    private String email;
    private String numberPhone;
    private String address;
    private LocalDate birthDate;
}
