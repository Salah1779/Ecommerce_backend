package com.ilisi.Ecommerce.services.mapper;

import com.ilisi.Ecommerce.bo.Client;
import com.ilisi.Ecommerce.dto.ClientDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements IMapper<Client, ClientDTO> {
    @Override
    public Client toBO(ClientDTO dto) {
        Client bo=new Client();
        bo.setClientID(dto.getClientID());
        bo.setFull_name(dto.getFull_name());
        bo.setEmail(dto.getEmail());
        bo.setLogin(dto.getLogin());
        bo.setPassword(dto.getPassword());

        return bo;
    }
    @Override
    public ClientDTO toDTO(Client bo) {
        ClientDTO dto=new ClientDTO();
        dto.setClientID(bo.getClientID());
        dto.setFull_name(bo.getFull_name());
        dto.setEmail(bo.getEmail());
        dto.setLogin(bo.getLogin());
        dto.setPassword(bo.getPassword());
        return dto;
    }
}
