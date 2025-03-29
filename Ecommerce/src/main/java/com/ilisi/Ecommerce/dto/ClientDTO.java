package com.ilisi.Ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private int clientID;
    private String full_name;
    private String email;
    private String login;
    private String password;
    private Date createddate;
    private Set<OrderDTO> orders;


}