package com.ilisi.Ecommerce.repository;

import com.ilisi.Ecommerce.bo.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
}
