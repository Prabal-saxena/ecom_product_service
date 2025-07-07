package com.ecommerce.product_service.dto;

import com.ecommerce.product_service.model.Category;
import com.ecommerce.product_service.model.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private String category;
    private String subCategory;
    private int quantity;
    private int rating;
    private String country;
    private String type;
    private float alcoholVol;
    private String creator;
    private List<String> tags;
}
