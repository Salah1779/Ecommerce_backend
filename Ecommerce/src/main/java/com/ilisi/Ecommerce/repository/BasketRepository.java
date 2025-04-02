package com.ilisi.Ecommerce.repository;

import com.ilisi.Ecommerce.bo.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {
    @Query("SELECT b FROM Basket b WHERE b.client.clientID = ?1")
    Optional<Basket> findByClient(int clientID);
}


