package com.ilisi.Ecommerce.dto;

import com.ilisi.Ecommerce.bo.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDTO {
    private OrderDTO orderID;
    private ProductDTO productID;
    private int quantity;
    private double amount;


}