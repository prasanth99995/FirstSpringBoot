package com.FirstSpringBoot.service;



import com.FirstSpringBoot.exceptions.ProductNotFoundException;
import com.FirstSpringBoot.model.Product;
import com.FirstSpringBoot.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProductService(Product product) {
        return productRepository.save(product);


    }

    public Product getProductService(int id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new ProductNotFoundException("The product with given id is not found");
        }
    }

    public ResponseEntity deleteProductService(int id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = null;
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            product = optionalProduct.get();
            return ResponseEntity.ok(product);
        } else {
            throw new ProductNotFoundException("Product with given Id is not available");
        }

    }
}

