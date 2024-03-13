package com.distritubuteddatabase.supplierservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WasteRequest {
    private String type; // e.g., organic, plastic, paper
    private double weight; // weight of the waste item
    private String supplierId; // ID of the waste supplier
    private String status; // e.g., pending, processed
}
