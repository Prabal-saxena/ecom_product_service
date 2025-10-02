package com.ecommerce.product_service.repository;

import com.ecommerce.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ProductRepository extends JpaRepository<Product,String> {
    Optional<Product> findByName(String name);

    List<Product> findByCategoryIdAndSubCategoryId(String categoryId, String subCategoryId);

    List<Product> findByCategoryId(String categoryId);
}
