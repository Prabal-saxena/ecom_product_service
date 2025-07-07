package com.ecommerce.product_service.controller;

import java.util.List;
import java.util.Optional;

import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.dto.ProductResponse;
import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.pagination.Page;
import com.ecommerce.product_service.pagination.Pageable;
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
    public Page<ProductResponse> getPaginatedProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String subCategory,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "6") int size
    ){
        System.out.println("Received request for products - Page: " + page + ", Size: " + size);
        Pageable pageable = Pageable.of(page, size);
        return productService.getProductPaginated(pageable, category, subCategory);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Optional<Product> updateProduct(@RequestBody ProductRequest productRequest){
        return productService.updateProduct(productRequest);
    }
}
