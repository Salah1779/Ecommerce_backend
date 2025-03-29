package com.ilisi.Ecommerce.bo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "basket")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Basket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int basketID;

    @Enumerated(EnumType.STRING)
    private BasketState basketState;

    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientID")
    private Client client;
    @OneToMany(mappedBy = "basket" , fetch = FetchType.EAGER)
    private Set<LineBasket> lineBaskets;


}