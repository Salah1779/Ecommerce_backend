package com.ilisi.Ecommerce.bo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor

public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productID")
	private int productID;
	private String label;
	private String description;
	private String image;
	private double retailPrice;
	private int stockQuantity;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryID")
	private Category category;


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
		this.retailPrice = retailPrice;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}