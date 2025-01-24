package org.gatodev.arcadiaclinica.service.persons;

import org.gatodev.arcadiaclinica.entity.persons.Person;
import java.util.regex.Pattern;

public interface IPersonService<T extends Person> {

    void checkSaveEntity(T person);

    void checkUpdateEntity(T person, T updatedPerson);

    default void checkSavePerson(Person person) {
        if (isValidEmail(person.getEmail()))
            throw new IllegalArgumentException("Invalid email format");
        if (verificateStringField(person.getDni()))
            throw new IllegalArgumentException("DNI is required");
        if (verificateStringField(person.getEmail()))
            throw new IllegalArgumentException("Email is required");
        if (verificateStringField(person.getFirstname()))
            throw new IllegalArgumentException("Firstname is required");
        if (verificateStringField(person.getLastname()))
            throw new IllegalArgumentException("Lastname is required");
    }

    default void checkUpdatePerson(Person person, Person updatedPerson) {
        if (isValidEmail(person.getEmail()))
            throw new IllegalArgumentException("Invalid email format");

        if (verificateChangeField(person.getDni(), updatedPerson.getDni()))
            person.setDni(updatedPerson.getDni().trim());

        if (verificateChangeField(person.getEmail(), updatedPerson.getEmail()))
            person.setEmail(updatedPerson.getEmail().trim());

        if (verificateChangeField(person.getFirstname(), updatedPerson.getFirstname()))
            person.setFirstname(updatedPerson.getFirstname().trim());

        if (verificateChangeField(person.getLastname(), updatedPerson.getLastname()))
            person.setLastname(updatedPerson.getLastname().trim());

        if (verificateChangeField(person.getNumberPhone(), updatedPerson.getNumberPhone()))
            person.setNumberPhone(updatedPerson.getNumberPhone().trim());

        if (verificateChangeField(person.getAddress(), updatedPerson.getAddress()))
            person.setAddress(updatedPerson.getAddress().trim());

        if (updatedPerson.getBirthDate() != null && !updatedPerson.getBirthDate().equals(person.getBirthDate()))
            person.setBirthDate(updatedPerson.getBirthDate());
    }

    default boolean verificateStringField(String field) {
        return field == null || field.isEmpty();
    }

    default boolean verificateChangeField(String oldValue, String newValue) {
        return newValue != null && !newValue.isEmpty() && !newValue.trim().equals(oldValue);
    }

    default boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return !pattern.matcher(email).matches();
    }
}
