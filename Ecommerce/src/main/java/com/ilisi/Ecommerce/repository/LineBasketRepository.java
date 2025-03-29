package com.ilisi.Ecommerce.repository;

import com.ilisi.Ecommerce.bo.LineBasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineBasketRepository extends JpaRepository<LineBasket, Long> {

}
