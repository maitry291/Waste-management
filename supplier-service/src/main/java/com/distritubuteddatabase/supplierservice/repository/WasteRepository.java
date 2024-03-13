package com.distritubuteddatabase.supplierservice.repository;

import com.distritubuteddatabase.supplierservice.model.Waste;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WasteRepository extends MongoRepository<Waste, String> {
}
