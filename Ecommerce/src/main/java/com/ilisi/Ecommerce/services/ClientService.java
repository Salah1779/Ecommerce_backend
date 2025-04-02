package com.ilisi.Ecommerce.services;

import com.ilisi.Ecommerce.dto.ClientDTO;
import com.ilisi.Ecommerce.exception.ResourceNotFoundException;
import com.ilisi.Ecommerce.services.mapper.ClientMapper;
import com.ilisi.Ecommerce.repository.ClientRepository;
import com.ilisi.Ecommerce.bo.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = clientMapper.toBO(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDTO(savedClient);
    }

    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(clientMapper::toDTO).collect(Collectors.toList());
    }

    public ClientDTO getClientById(int clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
        return clientMapper.toDTO(client);
    }

    public void deleteClient(int clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
        clientRepository.deleteById(clientId);
    }

    public ClientDTO updateClient(int clientId, ClientDTO clientDTO) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
        client.setFull_name(clientDTO.getFull_name());
        client.setEmail(clientDTO.getEmail());
        client.setLogin(clientDTO.getLogin());
        client.setPassword(clientDTO.getPassword());
        Client updatedClient = clientRepository.save(client);
        return clientMapper.toDTO(updatedClient);
    }
}