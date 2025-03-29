package com.ilisi.Ecommerce.dto;

import com.ilisi.Ecommerce.bo.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private int productID;
    private String label;
    private String description;
    private String image;
    private double retailPrice;
    private int stockQuantity;
    private CategoryDTO category;



    public void setRetailPrice(double retailPrice) {
        if(retailPrice < 0) retailPrice = 0;
        this.retailPrice = retailPrice;
    }

    public void setStockQuantity(int stockQuantity) {
        if(stockQuantity < 0) stockQuantity = 0;
        this.stockQuantity = stockQuantity;
    }

}