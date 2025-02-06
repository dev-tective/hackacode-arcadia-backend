package org.gatodev.arcadiaclinica.service.persons;

import org.gatodev.arcadiaclinica.DTO.persons.ClientDTO;
import org.gatodev.arcadiaclinica.DTO.persons.FullClientDTO;
import org.gatodev.arcadiaclinica.entity.persons.Client;
import java.util.List;

public interface IClientService {
    Client addClient(Client client);
    Client updateClient(ClientDTO client);
    Client getClientById(long id);
    Client getClientByDni(String dni);
    void deactivateClient(long id);
    void activateClient(long id);
    List<Client> getClients();
    ClientDTO getClientDTO(Client client);
    FullClientDTO getFullClientDTO(Client client);
}