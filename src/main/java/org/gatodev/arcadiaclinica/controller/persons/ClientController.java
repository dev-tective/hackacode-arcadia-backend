package org.gatodev.arcadiaclinica.controller.persons;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.entity.persons.Client;
import org.gatodev.arcadiaclinica.service.persons.IClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Client client) {
        return ResponseEntity.ok(clientService.addEntity(client));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(clientService.getAllEntities());
    }

    @GetMapping("dni/{dni}")
    public ResponseEntity<?> getById(@PathVariable String dni) {
        return ResponseEntity.ok(clientService.getEntityByDni(dni));
    }
}
