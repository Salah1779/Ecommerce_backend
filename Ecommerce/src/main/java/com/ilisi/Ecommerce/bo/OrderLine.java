package com.ilisi.Ecommerce.bo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;



@Entity
@Table(name = "orderLine")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderLineID;
    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "orderID")
    private Order order;
    private int quantity;
    private double amount;


}