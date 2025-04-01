package com.ilisi.Ecommerce.bo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryID")
    private int categoryID;
    private String label;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products;


}