package ru.asmisloff.spring.ht05.services;

import ru.asmisloff.spring.ht05.entities.Order;
import ru.asmisloff.spring.ht05.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

}
