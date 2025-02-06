package org.gatodev.arcadiaclinica.DTO.persons;

import lombok.*;
import org.gatodev.arcadiaclinica.entity.persons.Person;

@Getter
@Setter
public class ClientDTO extends Person {
    private String allergies;
    private Integer age;
    private Boolean healthInsurance;
}
