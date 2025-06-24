package com.ecommerce.product_service.repository;

import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.pagination.Page;
import com.ecommerce.product_service.pagination.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public interface ProductRepository extends MongoRepository<Product,String> {

    ConcurrentHashMap<Long, Product> products = new ConcurrentHashMap<>();
    Optional<Product> findByName(String name);

    default Page<Product> findAll(Pageable pageable){
        List<Product> allProducts = new ArrayList<>(findAll());

        int totalElements = allProducts.size();
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        int start = pageNumber + pageSize;
        int end = Math.min(start + pageSize, totalElements);

        if(start > totalElements){
            return new com.ecommerce.product_service.dto.PageImpl<>(Collections.emptyList(), pageable, totalElements);
        }

        List<Product> content = allProducts.subList(start, end);
        return new com.ecommerce.product_service.dto.PageImpl<>(content, pageable, totalElements);
    }
}
