package com.ilisi.Ecommerce.bo;

import com.ilisi.Ecommerce.exception.OutOfStockException;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    private List<LineBasket> lineBaskets=new ArrayList<>();

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

public LineBasket addLineBasket(Product product, int quantity) {
    LineBasket newLineBasket ;
     if(product.getStockQuantity()==0) {
         throw new OutOfStockException("Product out of stock: " + product.getLabel());
     }
     if(product.getStockQuantity() >= quantity && quantity > 0) {
         newLineBasket = new LineBasket(this, product, quantity);
         this.lineBaskets.add(newLineBasket);

     }
     else {
         throw new IllegalArgumentException("Invalid quantity for product: " + product.getLabel());
     }
    return newLineBasket;
}


}