package com.distritubuteddatabase.supplierservice.controller;

import com.distritubuteddatabase.supplierservice.dto.WasteRequest;
import com.distritubuteddatabase.supplierservice.dto.WasteResponse;
import com.distritubuteddatabase.supplierservice.model.Waste;
import com.distritubuteddatabase.supplierservice.service.WasteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/waste")
@RequiredArgsConstructor
public class WasteController {
    @Autowired
    private RestTemplate restTemplate;

    private final WasteService wasteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createWaste(@RequestBody WasteRequest wasteRequest) {
        Waste w1 = wasteService.createProduct(wasteRequest);
        //createInventory(p1);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WasteResponse> getAllProducts()
    {
        return wasteService.getAllProducts();
    }
}
