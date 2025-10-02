package com.ecommerce.product_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "t_category", schema = "product_service")
public class Category {

    @Id
    private String categoryId;
    @Column(nullable = false)
    private String category;
    @OneToMany(cascade = CascadeType.ALL)
    private List<SubCategory> subCategoryList;
}
