package com.ilisi.Ecommerce.bo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "basketLine")
@Data
@NoArgsConstructor
public class LineBasket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int basketLineID;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productID")
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basketID")
    private Basket basket;
    private int quantity;


   public  LineBasket(Basket basket, Product product,int quantity) {
        this.basket = basket;
        this.product = product;
        this.quantity = quantity;
    }

    public boolean decreaseQuantity() {
        if(quantity==0) return false;
        this.quantity--;
        return true;
    }

    public boolean increaseQuantity() {
        if(product.getStockQuantity() <= quantity) return false;
        this.quantity ++;
        return true;
    }
}