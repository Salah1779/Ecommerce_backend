package com.ilisi.Ecommerce.bo;

import com.ilisi.Ecommerce.exception.OutOfStockException;
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

    @OneToOne (fetch = FetchType.LAZY )
    @JoinColumn(name = "clientID")
    private Client client;
    @OneToMany(mappedBy = "basket" , fetch = FetchType.EAGER)
    private Set<LineBasket> lineBaskets;

 public void setEmptyState() {
     if(lineBaskets.isEmpty())
         basketState = BasketState.EMPTY;
 }
  public boolean isEmpty() {
     return lineBaskets.isEmpty();
 }

 public boolean isActive() {
     return basketState == BasketState.ACTIVE;
 }

 public void setActiveState() {
     if(!this.lineBaskets.isEmpty())
         basketState = BasketState.ACTIVE;
 }

 public void setValidatedState() {
     basketState = BasketState.VALIDATED;
 }

public void addLineBasket(Product product, int quantity) {
     if(product.getStockQuantity()==0) {
         throw new OutOfStockException("Product out of stock: " + product.getLabel());
     }
     if(product.getStockQuantity() >= quantity && quantity > 0) {
         LineBasket lineBasket = new LineBasket(this, product, quantity);
         this.lineBaskets.add(lineBasket);
     }
     else {
         throw new IllegalArgumentException("Invalid quantity for product: " + product.getLabel());
     }
}


}