package org.gatodev.arcadiaclinica.service.persons.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.DTO.persons.ClientDTO;
import org.gatodev.arcadiaclinica.entity.persons.Client;
import org.gatodev.arcadiaclinica.repository.persons.IClientRepository;
import org.gatodev.arcadiaclinica.service.persons.IClientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    public ClientServiceImpl(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client addClient(Client client) {
        client.validateFields(client.getEmail(), client.getDni(), client.getNumberPhone());
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        if (client.getId() == null || !clientRepository.existsById(client.getId())) {
            throw new EntityNotFoundException("Client not found");
        }
        client.validateFields(client.getEmail(), client.getDni(), client.getNumberPhone());
        client.setEnabled(true);
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    @Override
    public Client getClientByDni(String dni) {
        return clientRepository.findByDni(dni)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    @Override
    public void deactivateClient(long id) {
        Client client = getClientById(id);
        client.setEnabled(false);
        clientRepository.save(client);
    }

    @Override
    public void activateClient(long id) {
        Client client = getClientById(id);
        client.setEnabled(true);
        clientRepository.save(client);
    }

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }
}
