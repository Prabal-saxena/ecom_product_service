package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.CategoryResponse;
import com.ecommerce.product_service.model.Category;
import com.ecommerce.product_service.model.SubCategory;
import com.ecommerce.product_service.repository.CategoryRepository;
import com.ecommerce.product_service.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public List<CategoryResponse> getAllCategoriesAndSubCategories(){
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll();

        for (Category c: categoryList) {
            System.out.println(c.getCategoryId());
            List<SubCategory> subCategoryList = subCategoryRepository.findByCategoryId_CategoryId(c.getCategoryId());
            CategoryResponse categoryResponse = CategoryResponse.builder()
                    .categoryId(c.getCategoryId())
                    .category(c.getCategory())
                    .subCategories(subCategoryList)
                    .build();

            categoryResponseList.add(categoryResponse);
        }
        return categoryResponseList;
    }
}
