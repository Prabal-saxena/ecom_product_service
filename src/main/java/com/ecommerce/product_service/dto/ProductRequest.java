package com.ecommerce.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private String categoryId;
    private String subCategoryId;
    private int quantity;
    private int rating;
    private String country;
    private String type;
    private float alcoholVol;
    private String creator;
    private List<String> tags;
}
