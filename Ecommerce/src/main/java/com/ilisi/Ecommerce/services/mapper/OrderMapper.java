package com.ilisi.Ecommerce.services.mapper;

import com.ilisi.Ecommerce.bo.Order;
import com.ilisi.Ecommerce.dto.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }
        OrderDTO dto = new OrderDTO();
        dto.setOrderID(order.getOrderID());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotal(order.getTotal());
        // Assuming ClientMapper is available for mapping client
        // dto.setClient(clientMapper.toDTO(order.getClient()));
        return dto;
    }

    public Order toBO(OrderDTO dto) {
        if (dto == null) {
            return null;
        }
        Order order = new Order();
        order.setOrderID(dto.getOrderID());
        order.setOrderDate(dto.getOrderDate());
        order.setTotal(dto.getTotal());
        // Assuming ClientMapper is available for mapping client
        // order.setClient(clientMapper.toBO(dto.getClient()));
        return order;
    }
}