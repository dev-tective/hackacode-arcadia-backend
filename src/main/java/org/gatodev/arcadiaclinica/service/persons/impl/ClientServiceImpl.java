package org.gatodev.arcadiaclinica.service.persons.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.DTO.persons.ClientDTO;
import org.gatodev.arcadiaclinica.DTO.persons.FullClientDTO;
import org.gatodev.arcadiaclinica.entity.persons.Client;
import org.gatodev.arcadiaclinica.repository.persons.IClientRepository;
import org.gatodev.arcadiaclinica.service.persons.IClientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public ClientServiceImpl(IClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(ClientDTO client) {
        return clientRepository.save(modelMapper.map(client, Client.class));
    }

    @Override
    public Client getClientById(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    @Override
    public Client getClientByDni(String dni) {
        return clientRepository.findByAttributes_Dni(dni)
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

    @Override
    public ClientDTO getClientDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public FullClientDTO getFullClientDTO(Client client) {
        return modelMapper.map(client, FullClientDTO.class);
    }
}
