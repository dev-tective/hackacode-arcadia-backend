package org.gatodev.arcadiaclinica.service.persons.impl;

import org.gatodev.arcadiaclinica.entity.persons.Client;
import org.gatodev.arcadiaclinica.repository.persons.IClientRepository;
import org.gatodev.arcadiaclinica.service.persons.IClientService;
import org.gatodev.arcadiaclinica.service.persons.IPersonService;
import org.gatodev.arcadiaclinica.service.persons.IUserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientServiceImpl implements IClientService, IPersonService<Client> {

    private final IClientRepository clientRepository;
    private final IUserService userService;

    public ClientServiceImpl(IClientRepository clientRepository, IUserService userService) {
        this.clientRepository = clientRepository;
        this.userService = userService;
    }

    @Override
    public Client addClient(Client client) {
        checkSavePerson(client);
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        Client oldClient = getClientByDni(client.getDni());
        checkUpdatePerson(oldClient, client);
        return clientRepository.save(oldClient);
    }

    @Override
    public Client getClientByDni(String dni) {
        return clientRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("No client found with dni: " + dni));
    }

    @Override
    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No client found with email: " + email));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> getAllClientsByFirstName(String firstName) {
        return clientRepository.findAllByFirstname(firstName);
    }

    @Override
    public List<Client> getAllClientsByLastName(String lastName) {
        return clientRepository.findAllByLastname(lastName);
    }

    @Override
    public List<Client> getAllClientsByFirstNameAndLastName(String firstName, String lastName) {
        return clientRepository.findAllByFirstnameAndLastname(firstName, lastName);
    }

    // Revisa campos vacíos y existencias antes de guardar y actualizar
    @Override
    public void checkSaveEntity(Client person) {
        userService.checkSaveEntity(person);
    }

    @Override
    public void checkUpdateEntity(Client person, Client updatedPerson) {
        userService.checkUpdateEntity(person, updatedPerson);
    }
}
