package org.mrpo.lab1.controllers;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.dtos.OrderDto;
import org.mrpo.lab1.exceptions.AppException;
import org.mrpo.lab1.models.Order;
import org.mrpo.lab1.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get")
    public List<Order> getUserOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> principal = (HashMap<String, String>) authentication.getPrincipal();
        return orderService.findAllByUserId(Long.valueOf(principal.get("id")));
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/ids")
    public List<Long> getAllIds() {
        return orderService.findAllIds();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") long id) {
        return orderService.findById(id);
    }

    @PutMapping("/save")
    public ResponseEntity<?> saveOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(Map.of("id", orderService.save(orderDto)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") long id) {
        if(getAllIds().contains(id) ) {
            orderService.delete(id);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(new AppException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Article not exists"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}