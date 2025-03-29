package com.ilisi.Ecommerce.dto;

import com.ilisi.Ecommerce.bo.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private int orderID;
    private Date orderDate;
    private double total;
    private ClientDTO client;


}