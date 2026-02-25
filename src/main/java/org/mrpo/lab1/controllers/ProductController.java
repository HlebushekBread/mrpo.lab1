package org.mrpo.lab1.controllers;

import org.mrpo.lab1.repositories.ProductRepository;
import org.mrpo.lab1.models.Product;
import org.mrpo.lab1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String viewProductsPage(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products/show";
    }
}
