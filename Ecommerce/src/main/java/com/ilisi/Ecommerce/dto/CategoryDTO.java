package com.ilisi.Ecommerce.dto;


public class CategoryDTO {
    private int categoryID;
    private String label;
    public int getCategoryID() {
        return categoryID;
    }

    public CategoryDTO() {}
    public CategoryDTO(int categoryID, String label) {
        this.categoryID = categoryID;
        this.label = label;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}