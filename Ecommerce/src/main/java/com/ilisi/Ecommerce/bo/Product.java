package com.ilisi.Ecommerce.bo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
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

}