package com.FirstSpringBoot.controller;


import com.FirstSpringBoot.exceptions.ProductNotFoundException;
import com.FirstSpringBoot.model.Product;
import com.FirstSpringBoot.repository.ProductRepository;
import com.FirstSpringBoot.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class FirstController {
    private final ProductService service;
    private final ProductRepository repository;

    public FirstController(ProductService service, ProductRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/products")
    public ResponseEntity<Object> newProduct(@RequestBody Product product) {
        Product result = service.saveProductService(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(repository.findById(product.getId())).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") int id) throws ProductNotFoundException {
        return service.getProductService(id);

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") int id) throws ProductNotFoundException {
        return service.deleteProductService(id);
    }
}
