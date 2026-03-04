package org.mrpo.lab1.controllers;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.models.Provider;
import org.mrpo.lab1.services.ProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/providers")
public class ProviderController {
    private final ProviderService providerService;

    @GetMapping("/catalog")
    public List<Provider> getProvidersCatalog() {
        return providerService.findAll();
    }
}
