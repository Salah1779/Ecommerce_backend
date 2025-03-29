package com.ilisi.Ecommerce.repository;

import com.ilisi.Ecommerce.bo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
