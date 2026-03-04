package org.mrpo.lab1.services;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.models.Order;
import org.mrpo.lab1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAllByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(long id) {
        return orderRepository.findById(id).orElse(null);
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
