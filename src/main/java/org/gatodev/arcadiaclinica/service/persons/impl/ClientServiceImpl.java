package org.gatodev.arcadiaclinica.service.persons.impl;

import org.gatodev.arcadiaclinica.entity.persons.Client;
import org.gatodev.arcadiaclinica.repository.persons.IClientRepository;
import org.gatodev.arcadiaclinica.service.persons.IClientService;
import org.gatodev.arcadiaclinica.service.persons.IUserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    public ClientServiceImpl(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client addEntity(Client person) {
        verificatePerson(person);
        IUserService.encryptPassword(person);
        return clientRepository.save(person);
    }

    @Override
    public Client updateEntity(Client person) {
        if (!clientRepository.existsById(person.getId())) {
            throw new RuntimeException("Client with id " + person.getId() + " not found");
        }
        verificatePerson(person);
        IUserService.encryptPassword(person);
        return clientRepository.save(person);
    }

    @Override
    public Client getEntityByDni(String dni) {
        return clientRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Dni not found"));
    }

    @Override
    public Client getEntityByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));
    }

    @Override
    public Client getEntityById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found"));
    }

    @Override
    public List<Client> getAllEntities() {
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
}
