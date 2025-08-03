package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.dto.ProductResponse;
import com.ecommerce.product_service.model.Category;
import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.model.SubCategory;
import com.ecommerce.product_service.pagination.Page;
import com.ecommerce.product_service.pagination.Pageable;
import com.ecommerce.product_service.repository.CategoryRepository;
import com.ecommerce.product_service.repository.ProductRepository;
import com.ecommerce.product_service.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .imageUrl(productRequest.getImageUrl())
                .category(Category.builder().categoryId(productRequest.getCategoryId()).build())
                .subCategory(SubCategory.builder().subCategoryId(productRequest.getSubCategoryId()).build())
                .quantity(productRequest.getQuantity())
                .rating(productRequest.getRating())
                .country(productRequest.getCountry())
                .type(productRequest.getType())
                .alcoholVol(productRequest.getAlcoholVol())
                .creator(productRequest.getCreator())
                .tags(productRequest.getTags())
                .build();

        Category cat = categoryRepository.findById(product.getCategory().getCategoryId())
                .orElseThrow(() -> new RuntimeException(("CategoryId not Found")));
        SubCategory subCat = subCategoryRepository.findById(product.getSubCategory().getSubCategoryId())
                .orElseThrow(() -> new RuntimeException("SubCategory not found."));

        product.setCategory(cat);
        product.setSubCategory(subCat);
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
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

    public Page<ProductResponse> getProductPaginated(Pageable pageable, String categoryId, String subCategoryId){
        Page<Product> paginatedProducts = null;
        List<ProductResponse> productResponses;

        try {
            if (categoryId != null && !categoryId.isEmpty()) {
                if (subCategoryId != null && !subCategoryId.isEmpty()) {
                    paginatedProducts = paginationProcess(productRepository.findByCategory_categoryIdAndSubCategory_subCategoryId(categoryId, subCategoryId), pageable);
                }else {
                    paginatedProducts = paginationProcess(productRepository.findByCategory_categoryId(categoryId), pageable);
                }
            } else {
                paginatedProducts = paginationProcess(productRepository.findAll(), pageable);
            }
        }catch (Exception ex){System.out.println(ex.getMessage());}

        if (paginatedProducts != null || paginatedProducts.getContent() != null || paginatedProducts.getNumber() != 0 || paginatedProducts.getTotalPages() !=0){
            productResponses = mappingProductResponse(paginatedProducts);
            return new com.ecommerce.product_service.dto.PageImpl<>(productResponses, pageable, paginatedProducts.getTotalElements());
        }else {
            return new com.ecommerce.product_service.dto.PageImpl<>(Collections.emptyList(), pageable, paginatedProducts.getTotalElements());
        }
    }

    public List<Product> getProductsByIds(List<String> productIds){
        return productRepository.findAllById(productIds);
    }

    public Product getProductByProductId(String productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Couldn't find the product with productId: " + productId));
        return product;
    }

    private Page<Product> paginationProcess(List<Product> allProducts, Pageable pageable){

        int totalElements = allProducts.size();
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        int start = pageNumber * pageSize;
        int end = Math.min(start + pageSize, totalElements);

        if(start > totalElements){
            return new com.ecommerce.product_service.dto.PageImpl<>(Collections.emptyList(), pageable, totalElements);
        }

        return new com.ecommerce.product_service.dto.PageImpl<>(allProducts.subList(start, end), pageable, totalElements);
    }

    private List<ProductResponse> mappingProductResponse(Page<Product> paginatedProducts){
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product p: paginatedProducts.getContent()) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(p.getId());
            productResponse.setName(p.getName());
            productResponse.setDescription(p.getDescription());
            productResponse.setPrice(p.getPrice());
            productResponse.setImageUrl(p.getImageUrl());
            productResponse.setCategory(p.getCategory().getCategory());
            productResponse.setSubCategory(p.getSubCategory().getSubCategory());
            productResponse.setQuantity(p.getQuantity());
            productResponse.setRating(p.getRating());
            productResponse.setCountry(p.getCountry());
            productResponse.setType(p.getType());
            productResponse.setAlcoholVol(p.getAlcoholVol());
            productResponse.setCreator(p.getCreator());
            if (!p.getTags().isEmpty()) {
                productResponse.setTags(p.getTags());
            }

            productResponses.add(productResponse);
        }
        return productResponses;
    }
}
