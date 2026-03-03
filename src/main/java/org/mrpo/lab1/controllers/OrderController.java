package org.mrpo.lab1.controllers;

import org.mrpo.lab1.models.Order;
import org.mrpo.lab1.security.UserDetailsImpl;
import org.mrpo.lab1.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.synth.SynthTableHeaderUI;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get")
    public List<Order> viewUserProductsPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> principal = (HashMap<String, String>) authentication.getPrincipal();
        return orderService.findAllByUserId(Long.valueOf(principal.get("id")));
    }

    @GetMapping("/all")
    public List<Order> viewAllProductsPage() {
        return orderService.findAll();
    }
}