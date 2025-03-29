package com.ilisi.Ecommerce.dto;

import com.ilisi.Ecommerce.bo.Basket;
import com.ilisi.Ecommerce.bo.BasketState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDTO {
    private int basketID;
    private BasketState basketState;
    private Date createdDate;
    private ClientDTO client;

}