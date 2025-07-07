package com.ecommerce.product_service.dto;

import com.ecommerce.product_service.model.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private String categoryId;
    private String category;
    private List<SubCategory> subCategories;
}
