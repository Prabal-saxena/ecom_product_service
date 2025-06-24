package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.dto.ProductResponse;
import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.pagination.Page;
import com.ecommerce.product_service.pagination.Pageable;
import com.ecommerce.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .imageUrl(productRequest.getImageUrl())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mapToProductResponse(product)).toList();
    }

    public Optional<Product> updateProduct(ProductRequest productRequest){

        if(productRequest == null || productRequest.getName() == null || productRequest.getName().trim().isEmpty()){
            System.err.println("Error: ProductRequest or product name for update cannot be null or empty.");
            return Optional.empty();
        }

        Optional<Product> existingProduct= productRepository.findByName(productRequest.getName());
        if(existingProduct.isPresent()) {
            Product product = existingProduct.get();
            System.out.println("Found product " + productRequest.getName() + " now updating details...");

            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setImageUrl(productRequest.getImageUrl());

            Product updatedProduct = productRepository.save(product);
            System.out.println("Product '" + productRequest.getName() + "' updated successfully.");
            return Optional.of(updatedProduct);
        }else {
            // Product not found
            System.out.println("Product with name '" + productRequest.getName() + "' not found for update.");
            return Optional.empty(); // Indicate that the product was not found
        }

    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();
    }

    public Page<Product> getProductPaginated(Pageable pageable){
        return productRepository.findAll(pageable);
    }
}
