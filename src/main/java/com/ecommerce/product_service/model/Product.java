package com.ecommerce.product_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    @DBRef
    private Category category;
    @DBRef
    private SubCategory subCategory;
    private int quantity;
    private int rating;
    private String country;
    private String type;
    private float alcoholVol;
    private String creator;
    private List<String> tags;
}
