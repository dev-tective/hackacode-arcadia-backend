package org.gatodev.arcadiaclinica.service.persons;

import org.gatodev.arcadiaclinica.entity.business.Receipt;
import org.gatodev.arcadiaclinica.entity.persons.Client;

import java.util.List;

public interface IClientService {
    Client addClient(Client client);

    Client updateClient(Client client);

    Client getClientByDni(String dni);

    Client getClientByEmail(String email);

    List<Client> getAllClients();

    List<Client> getAllClientsByFirstName(String firstName);

    List<Client> getAllClientsByLastName(String lastName);

    List<Client> getAllClientsByFirstNameAndLastName(String firstName, String lastName);

//    Manejo de recibos
//    Client addReceiptToClient(String dni, Long idReceipt);
}
