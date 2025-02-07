package org.gatodev.arcadiaclinica.controller.persons;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.DTO.persons.ClientDTO;
import org.gatodev.arcadiaclinica.entity.persons.Client;
import org.gatodev.arcadiaclinica.service.persons.IClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<Client> getClientByDni(@PathVariable String dni) {
        return ResponseEntity.ok(clientService.getClientByDni(dni));
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@Valid @RequestBody Client client) {
        return ResponseEntity.ok(clientService.addClient(client));
    }

    @PutMapping
    public ResponseEntity<Client> updateClient(@Valid @RequestBody Client client) {
        return ResponseEntity.ok(clientService.updateClient(client));
    }

    @PatchMapping("deactivate/{id}")
    public ResponseEntity<?> deactivateClient(@PathVariable long id) {
        clientService.deactivateClient(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("activate/{id}")
    public ResponseEntity<?> activateClient(@PathVariable long id) {
        clientService.activateClient(id);
        return ResponseEntity.noContent().build();
    }
}
