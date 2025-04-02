package com.ilisi.Ecommerce.repository;

import com.ilisi.Ecommerce.bo.LineBasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineBasketRepository extends JpaRepository<LineBasket, Integer> {

    @Query("SELECT l FROM LineBasket l WHERE l.basket.basketID = ?1")
    List<LineBasket> findAllByBasketID(Integer basketId);

}
