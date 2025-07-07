package com.ecommerce.product_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(value = "subcategory")
public class SubCategory {

    @Id
    private String subCategoryId;
    private String subCategory;
    private String categoryId;
}
