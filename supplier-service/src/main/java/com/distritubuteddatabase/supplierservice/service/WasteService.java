package com.distritubuteddatabase.supplierservice.service;

import com.distritubuteddatabase.supplierservice.dto.WasteRequest;
import com.distritubuteddatabase.supplierservice.dto.WasteResponse;
import com.distritubuteddatabase.supplierservice.model.Waste;
import com.distritubuteddatabase.supplierservice.repository.WasteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WasteService {

    private final WasteRepository wasteRepository;

    public Waste createProduct(WasteRequest wasteRequest) {
        Waste product = Waste.builder()
                .type(wasteRequest.getType())
                .weight(wasteRequest.getWeight())
                .supplierId(wasteRequest.getSupplierId())
                .status(wasteRequest.getStatus())
                .build();

        wasteRepository.save(product);
        log.info("Product {} is saved", product.getId());
        return product;
    }

    public List<WasteResponse> getAllProducts() {
        List<Waste> products = wasteRepository.findAll();

        return products.stream().map(this::mapToWasteResponse).toList();
    }

    private WasteResponse mapToWasteResponse(Waste waste) {
        return WasteResponse.builder()
                .id(waste.getId())
                .type(waste.getType())
                .weight(waste.getWeight())
                .supplierId(waste.getSupplierId())
                .status(waste.getStatus())
                .build();
    }
}
