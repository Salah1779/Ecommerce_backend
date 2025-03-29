package com.ilisi.Ecommerce.dto;

import com.ilisi.Ecommerce.bo.OrderLine;
import lombok.Data;

@Data
public class OrderLineDTO {
    private OrderDTO orderID;
    private ProductDTO productID;
    private int quantity;
    private double amount;


}