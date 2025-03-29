package com.ilisi.Ecommerce.dto;

import com.ilisi.Ecommerce.bo.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private int productID;
    private String label;
    private String description;
    private String image;
    private double retailPrice;
    private int stockQuantity;
    private CategoryDTO category;


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        if(retailPrice < 0) retailPrice = 0;
        this.retailPrice = retailPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        if(stockQuantity < 0) stockQuantity = 0;
        this.stockQuantity = stockQuantity;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}