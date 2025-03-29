package com.ilisi.Ecommerce.bo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "categories")
public class Category implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryID")
    private int categoryID;
    private String label;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products;
    public int getCategoryID() {
        return categoryID;
    }

    public Category() {}

    public Category(int categoryID, String label) {
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}