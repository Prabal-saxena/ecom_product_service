package com.ecommerce.product_service.repository;

import com.ecommerce.product_service.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, String> {

    List<SubCategory> findByCategoryId(String categoryId);
}
