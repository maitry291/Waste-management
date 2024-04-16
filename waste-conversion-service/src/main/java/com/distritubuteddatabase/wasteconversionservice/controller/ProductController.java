package com.distritubuteddatabase.wasteconversionservice.controller;

import com.distritubuteddatabase.wasteconversionservice.dto.ProductRequest;
import com.distritubuteddatabase.wasteconversionservice.dto.ProductResponse;
import com.distritubuteddatabase.wasteconversionservice.dto.WasteRequest;
import com.distritubuteddatabase.wasteconversionservice.model.Inventory;
import com.distritubuteddatabase.wasteconversionservice.model.Product;
import com.distritubuteddatabase.wasteconversionservice.model.Waste;
import com.distritubuteddatabase.wasteconversionservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        Product p1 = productService.createProduct(productRequest);
        createInventory(p1);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts()
    {
        return productService.getAllProducts();
    }

    public void createInventory(Product product)
    {

        String url = "http://localhost:8081/api/inventory";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Inventory inventory = new Inventory();

        inventory.setProductId(product.getId());
        inventory.setName(product.getName());
        inventory.setQuantity(1);

        HttpEntity<Inventory> requestEntity = new HttpEntity<>(inventory, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, requestEntity, Void.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Inventory created successfully.");
        } else {
            System.err.println("Failed to create inventory.");
        }
    }
/*
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    private void updateWasteStatus(WasteRequest wasteRequest)
    {
        Waste waste = Waste.builder()
                .wasteId(wasteRequest.getWasteId())
                .type(wasteRequest.getType())
                .weight(wasteRequest.getWeight())
                .supplierId(wasteRequest.getSupplierId())
                .status(wasteRequest.getStatus())
                .build();

        String getUrl = "http://localhost:8084/api/waste/" + waste.getWasteId();
        Waste response = restTemplate.getForObject(getUrl, Waste.class);

        assert response != null;
        response.setStatus(waste.getStatus());

        String putUrl = "http://localhost:8081/api/waste/put";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(response, headers);
        ResponseEntity<Void> resp1 = restTemplate.exchange(
                putUrl,
                HttpMethod.PUT,
                requestEntity,
                Void.class);
        if (resp1.getStatusCode() == HttpStatus.OK) {
            System.out.println("Hurray Success");
        } else {
            System.out.println("Shit Error");
        }
    }*/

}
