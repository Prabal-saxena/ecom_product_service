package com.ecommerce.product_service.repository;

import com.ecommerce.product_service.model.Category;
import com.ecommerce.product_service.model.SubCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends MongoRepository<SubCategory, String> {

    @Query("{ 'categoryId.categoryId': ?0 }")
    List<SubCategory> findByCategoryId_CategoryId(String categoryId);
}
