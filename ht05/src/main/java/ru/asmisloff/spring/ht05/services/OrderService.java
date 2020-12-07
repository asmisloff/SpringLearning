package ru.asmisloff.spring.ht05.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Order> findAll(Specification<Order> spec, int page, int size) {
        return orderRepository.findAll(spec, PageRequest.of(page, size));
    }

}
