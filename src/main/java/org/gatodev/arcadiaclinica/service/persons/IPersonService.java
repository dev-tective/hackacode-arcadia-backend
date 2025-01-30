package org.gatodev.arcadiaclinica.service.persons;

import org.gatodev.arcadiaclinica.entity.persons.Person;
import java.util.List;

public interface IPersonService<T extends Person> {
    T addEntity(T person);

    T updateEntity(T person);

    T getEntityByDni(String dni);

    T getEntityById(long id);

    List<T> getAllEntities();

    default void verificatePerson(T person) {
        if (person.isValidEmail(person.getEmail())) {
            throw new RuntimeException("Invalid email");
        }
        if (!person.isValidDni(person.getDni())) {
            throw new RuntimeException("Invalid dni");
        }
        if (person.getNumberPhone() != null) {
            if (!person.isValidNumberPhone(person.getNumberPhone())) {
                throw new RuntimeException("Invalid phone number");
            }
        }
    }
}
