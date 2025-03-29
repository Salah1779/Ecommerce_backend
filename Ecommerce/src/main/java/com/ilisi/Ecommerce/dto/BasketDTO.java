package com.ilisi.Ecommerce.dto;

import com.ilisi.Ecommerce.bo.Basket;
import com.ilisi.Ecommerce.bo.BasketState;
import lombok.Data;

import java.util.Date;

@Data
public class BasketDTO {
    private int basketID;
    private BasketState basketState;
    private Date createdDate;
    private ClientDTO client;

}