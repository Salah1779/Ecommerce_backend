package com.ilisi.Ecommerce.services;

import com.ilisi.Ecommerce.dto.ClientDTO;
import com.ilisi.Ecommerce.services.mapper.ClientMapper;
import com.ilisi.Ecommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService
{
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

  public  ClientDTO saveClient(ClientDTO clientDTO){
      return clientMapper.toDTO(clientRepository.save(clientMapper.toBO(clientDTO)));
}

}
