package org.mrpo.lab1.controllers;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.models.Unit;
import org.mrpo.lab1.services.UnitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/units")
public class UnitController {
    private final UnitService unitService;

    @GetMapping("/catalog")
    public List<Unit> getUnitsCatalog() {
        return unitService.findAll();
    }
}
