package com.ilisi.Ecommerce.services;

import com.ilisi.Ecommerce.dto.OrderDTO;
import com.ilisi.Ecommerce.bo.Order;
import com.ilisi.Ecommerce.services.mapper.OrderMapper;
import com.ilisi.Ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private  OrderRepository orderRepository;
    @Autowired
    private  OrderMapper orderMapper;



    // ✅ Get all orders
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    // ✅ Get orders by client ID
    public List<OrderDTO> getOrdersByClient(Long clientId) {
        List<Order> orders = orderRepository.findByClientId(clientId);
        return orders.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    // ✅ Save a new order
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toBO(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }

    // ✅ Delete an order
    public boolean deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            return false; // Order not found
        }
        orderRepository.deleteById(orderId);
        return true;
    }

    // ✅ Update order state
    public OrderDTO updateOrderState(Long orderId, String newState) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setState(newState);
            Order updatedOrder = orderRepository.save(order);
            return orderMapper.toDTO(updatedOrder);
        } else {
            return null; // Order not found
        }
    }
}
