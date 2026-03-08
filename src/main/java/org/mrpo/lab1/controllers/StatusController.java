package org.mrpo.lab1.controllers;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.models.Status;
import org.mrpo.lab1.services.StatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/statuses")
public class StatusController {
    private final StatusService statusService;

    @GetMapping("/catalog")
    public List<Status> getStatusesCatalog() {
        return statusService.findAll();
    }
}
