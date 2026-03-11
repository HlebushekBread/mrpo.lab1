package org.mrpo.lab1.controllers;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.services.OrderProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/orderProducts")
public class OrderProductController {
    private final OrderProductService orderProductService;

    @GetMapping("/orders/{article}")
    public List<Long> getOrderIdsByProductArticle(@PathVariable("article") String article) {
        return orderProductService.findOrderIdsByProductArticle(article);
    }
}
