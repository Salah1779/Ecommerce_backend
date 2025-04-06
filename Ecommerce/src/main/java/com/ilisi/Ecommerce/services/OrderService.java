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



    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getOrdersByClient(int clientId) {
        List<Order> orders = orderRepository.findByClientId(clientId);
        return orders.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }


    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toBO(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }


    public boolean deleteOrder(int orderId) {
        if (!orderRepository.existsById(orderId)) {
            return false;
        }
        orderRepository.deleteById(orderId);
        return true;
    }

    public OrderDTO getOrderById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            return null;
        }
        return  orderMapper.toDTO(
                order.get()
        );

    }

    public OrderDTO updateOrder(int id, OrderDTO order) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isPresent()) {
            Order orderToUpdate = optionalOrder.get();
            orderToUpdate.setOrderDate(order.getOrderDate());
            orderToUpdate.setTotal(order.getTotal());
            Order updatedOrder = orderRepository.save(orderToUpdate);
            return orderMapper.toDTO(updatedOrder);
        } else {
            return null; // Order not found
        }
    }

    // ✅ Update order state
    //the Order class has any attribute that state

  /*  public OrderDTO updateOrderState(Long orderId, String newState) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setState(newState);
            Order updatedOrder = orderRepository.save(order);
            return orderMapper.toDTO(updatedOrder);
        } else {
            return null; // Order not found
        }
    }*/
}
