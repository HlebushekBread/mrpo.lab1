package org.mrpo.lab1.controllers;

import org.mrpo.lab1.repositories.OrderRepository;
import org.mrpo.lab1.models.Order;
import org.mrpo.lab1.security.UserDetailsImpl;
import org.mrpo.lab1.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/show")
    public String viewUserProductsPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<Order> orders = orderService.findAllByUserName(userDetails.getUser().getName());
        model.addAttribute("orders", orders);
        return "orders/show";
    }

    @GetMapping("/all")
    public String viewAllProductsPage(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "orders/show";
    }
}