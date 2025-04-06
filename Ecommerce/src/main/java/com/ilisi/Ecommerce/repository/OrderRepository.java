package com.ilisi.Ecommerce.repository;

import com.ilisi.Ecommerce.bo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("SELECT o FROM Order o WHERE o.client.clientID = ?1")
    List<Order> findByClientId(int clientId);
}
