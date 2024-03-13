package com.distritubuteddatabase.supplierservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "waste")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Waste {
    @Id
    private String id;

    private String type; // e.g., organic, plastic, paper
    private double weight; // weight of the waste item
    private String supplierId; // ID of the waste supplier
    private String status; // e.g., pending, processed
}
