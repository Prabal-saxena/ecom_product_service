package com.ecommerce.product_service.repository;

import com.ecommerce.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.*;

public interface ProductRepository extends MongoRepository<Product,String> {
    Optional<Product> findByName(String name);

    List<Product> findByCategory_categoryIdAndSubCategory_subCategoryId(String categoryId, String subCategoryId);

    List<Product> findByCategory_categoryId(String categoryId);
}
