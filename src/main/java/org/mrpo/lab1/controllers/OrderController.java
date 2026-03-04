package org.mrpo.lab1.controllers;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.models.Order;
import org.mrpo.lab1.services.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

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