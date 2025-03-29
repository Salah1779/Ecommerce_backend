package com.ilisi.Ecommerce.dto;

import com.ilisi.Ecommerce.bo.LineBasket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineBasketDTO {
	private ProductDTO productID;
        private BasketDTO basketID;
	private int quantity;
	

}