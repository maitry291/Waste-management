package com.distritubuteddatabase.supplierservice.repository;

import com.distritubuteddatabase.supplierservice.model.Waste;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WasteRepository extends MongoRepository<Waste, String> {
    Optional<Waste> findByWasteId(String wasteId);
}
