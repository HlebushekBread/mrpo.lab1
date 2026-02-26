package org.mrpo.lab1.controllers;

import org.mrpo.lab1.models.Order;
import org.mrpo.lab1.security.UserDetailsImpl;
import org.mrpo.lab1.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/show")
    public List<Order> viewUserProductsPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return orderService.findAllByUserName(userDetails.getUser().getName());
    }

    @GetMapping("/all")
    public List<Order> viewAllProductsPage() {
        return orderService.findAll();
    }
}