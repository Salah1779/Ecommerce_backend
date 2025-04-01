package com.ilisi.Ecommerce.controller;

import com.ilisi.Ecommerce.dto.ClientDTO;
import com.ilisi.Ecommerce.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ClientDTO>> getClientById(@PathVariable Integer id) {
        Optional<ClientDTO> client = clientService.getClientById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO savedClient = clientService.saveClient(clientDTO);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Integer id, @RequestBody ClientDTO clientDTO) {
        ClientDTO updatedClient = clientService.updateClient(id, clientDTO);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}