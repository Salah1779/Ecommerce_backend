package com.ilisi.Ecommerce.repository;

import com.ilisi.Ecommerce.bo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer > {

    @Query("SELECT p FROM Product p WHERE p.category.categoryID = ?1")
     List<Product> filterProductsByCategory(Integer categoryID);

    @Query("SELECT p FROM Product p WHERE p.retailPrice BETWEEN ?1 AND ?2")
    List<Product> findByRetailPriceBetween(double minPrice,double maxPrice);

    @Query("SELECT p FROM Product p WHERE p.category.categoryID =?1 AND p.retailPrice BETWEEN ?2 AND ?3")
     List<Product> filterPriceCategory(Integer category , double minPrice , double maxPrice);

   @Query("SELECT p FROM Product p WHERE p.label LIKE %?1%")
    List<Product> findByLabelContainig(String label) ;
}
