package com.ilisi.Ecommerce.dto;

import com.ilisi.Ecommerce.bo.LineBasket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineBasketDTO {
	private int basketLineID;
	private ProductDTO product;
	private BasketDTO basket;
	private int quantity;
	

}