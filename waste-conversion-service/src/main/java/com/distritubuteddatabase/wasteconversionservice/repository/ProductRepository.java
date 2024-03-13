package com.distritubuteddatabase.wasteconversionservice.repository;

import com.distritubuteddatabase.wasteconversionservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}