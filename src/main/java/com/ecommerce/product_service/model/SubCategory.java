package com.ecommerce.product_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "t_subcategory", schema = "product_service")
public class SubCategory {

    @Id
    private String subCategoryId;
    @Column(nullable = false)
    private String subCategory;
    @Column(nullable = false)
    private String categoryId;
}
