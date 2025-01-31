package org.gatodev.arcadiaclinica.service.persons.impl;

import org.gatodev.arcadiaclinica.entity.persons.Client;
import org.gatodev.arcadiaclinica.entity.persons.Role;
import org.gatodev.arcadiaclinica.repository.persons.IClientRepository;
import org.gatodev.arcadiaclinica.service.persons.IClientService;
import org.gatodev.arcadiaclinica.service.persons.IRoleService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    public ClientServiceImpl(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client addEntity(Client person) {
        person.validateAge();
        verificatePerson(person);
        return clientRepository.save(person);
    }

    @Override
    public Client updateEntity(Client person) {
        if (!clientRepository.existsById(person.getId())) {
            throw new RuntimeException("Client with id " + person.getId() + " not found");
        }
        person.validateAge();
        verificatePerson(person);
        return clientRepository.save(person);
    }

    @Override
    public Client getEntityByDni(String dni) {
        return clientRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Dni not found"));
    }

    @Override
    public Client getEntityById(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found"));
    }

    @Override
    public List<Client> getAllEntities() {
        return clientRepository.findAll();
    }
}
