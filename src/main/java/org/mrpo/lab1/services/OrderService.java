package org.mrpo.lab1.services;

import org.mrpo.lab1.models.Order;
import org.mrpo.lab1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAllByUserName(String userName) {
        return orderRepository.findAllByUserName(userName);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    @Transactional
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public void update(long id, Order order) {
        order.setId(id);
        orderRepository.save(order);
    }

    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
