package com.ecommerce.product_service.controller;

import java.util.List;
import java.util.Optional;

import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.dto.ProductResponse;
import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:63342") // Allow requests from this origin
    public List<ProductResponse> getProduct(){
        return productService.getAllProducts();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Optional<Product> updateProduct(@RequestBody ProductRequest productRequest){
        return productService.updateProduct(productRequest);
    }
}
