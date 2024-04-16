package com.distritubuteddatabase.supplierservice.service;

import com.distritubuteddatabase.supplierservice.dto.WasteRequest;
import com.distritubuteddatabase.supplierservice.dto.WasteResponse;
import com.distritubuteddatabase.supplierservice.model.Waste;
import com.distritubuteddatabase.supplierservice.repository.WasteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WasteService {

    private final WasteRepository wasteRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Waste createProduct(WasteRequest wasteRequest) {
        Waste product = Waste.builder()
                .type(wasteRequest.getType())
                .weight(wasteRequest.getWeight())
                .supplierId(wasteRequest.getSupplierId())
                .status(wasteRequest.getStatus())
                .build();

        wasteRepository.save(product);
        log.info("Product {} is saved", product.getWasteId());
        return product;
    }

    public List<WasteResponse> getAllProducts() {
        List<Waste> products = wasteRepository.findAll();

        return products.stream().map(this::mapToWasteResponse).toList();
    }

    private WasteResponse mapToWasteResponse(Waste waste) {
        return WasteResponse.builder()
                .wasteId(waste.getWasteId())
                .type(waste.getType())
                .weight(waste.getWeight())
                .supplierId(waste.getSupplierId())
                .status(waste.getStatus())
                .build();
    }

    public Waste updateWasteItem(Waste product)
    {
        Query query = new Query().addCriteria(Criteria.where("wasteId").is(product.getWasteId()));
        Update update = new Update().set("status",product.getStatus());
        mongoTemplate.updateFirst(query,update,Waste.class);
        return product;
    }

    public Optional<Waste> getWasteItem(String id)
    {
        return wasteRepository.findByWasteId(id);
    }


}
