package org.mrpo.lab1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api")
public class MainController {
    @GetMapping("")
    public String redirect() {
        return "redirect:api/products/all";
    }
}
