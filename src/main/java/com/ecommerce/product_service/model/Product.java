package com.ecommerce.product_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
@Entity
@Table(name = "t_product", schema = "product_service")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private String categoryId;
    @Column(nullable = false)
    private String subCategoryId;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private int rating;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private float alcoholVol;
    @Column(nullable = false)
    private String creator;
    @Column(nullable = false)
    private List<String> tags;
}
