package com.ilisi.Ecommerce.bo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientID")
    private int clientID;
    private String full_name;
    private String email;
    private String login;
    private String password;
    private Date createddate;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Order> orders;


    public void setFull_name(String full_name)
    {
        this.full_name = full_name!=null ? full_name : "";
    }


}