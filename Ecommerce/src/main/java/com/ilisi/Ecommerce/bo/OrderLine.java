package com.ilisi.Ecommerce.bo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderLine")
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

    public int getOrderLineID() {
        return orderLineID;
    }

    public void setOrderLineID(int id) {
        this.orderLineID = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}