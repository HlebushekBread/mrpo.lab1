package org.mrpo.lab1.controllers;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.models.Address;
import org.mrpo.lab1.services.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/catalog")
    public List<Address> getAddressesCatalog() {
        return addressService.findAll();
    }
}
