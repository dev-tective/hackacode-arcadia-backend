package org.gatodev.arcadiaclinica.service.persons;

import org.gatodev.arcadiaclinica.entity.persons.Client;
import java.util.List;

public interface IClientService extends IPersonService<Client> {
    List<Client> getAllClientsByFirstName(String firstName);

    List<Client> getAllClientsByLastName(String lastName);

    List<Client> getAllClientsByFirstNameAndLastName(String firstName, String lastName);
}