package com.ilisi.Ecommerce.dto;

import com.ilisi.Ecommerce.bo.LineBasket;
import lombok.Data;

@Data
public class LineBasketDTO {
	private ProductDTO productID;
        private BasketDTO basketID;
	private int quantity;
	

}