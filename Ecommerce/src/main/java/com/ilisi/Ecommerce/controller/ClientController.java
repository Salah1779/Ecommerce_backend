package com.ilisi.Ecommerce.controller;

import com.ilisi.Ecommerce.dto.ClientDTO;
import com.ilisi.Ecommerce.services.ClientService;
import com.ilisi.Ecommerce.utils.Response;
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
    public ResponseEntity<Response> getAllClients() {
        List<ClientDTO> clients = clientService.getAllClients();
        Response result = new Response(true, HttpStatus.OK, "Clients List", clients);
        return new ResponseEntity<Response>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getClientById(@PathVariable Integer id) {
        ClientDTO client = clientService.getClientById(id);
        Response result = new Response(true, HttpStatus.OK, "Client found", client);
        return new ResponseEntity<Response>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> saveClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO savedClient = clientService.saveClient(clientDTO);
        Response res=new Response(true , HttpStatus.CREATED , "Client Created Successfully" , savedClient);
        return new ResponseEntity<Response>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateClient(@PathVariable Integer id, @RequestBody ClientDTO clientDTO) {
        ClientDTO updatedClient = clientService.updateClient(id, clientDTO);
        Response res = new Response(true, HttpStatus.OK, "Client Updated Successfully", updatedClient);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
        Response res = new Response(true, HttpStatus.OK, "Client with id " + id + " deleted successfully", null);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }
}