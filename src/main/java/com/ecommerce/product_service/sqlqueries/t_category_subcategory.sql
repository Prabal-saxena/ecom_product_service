CREATE TABLE product_service.t_subcategory (
    sub_category_id VARCHAR(50) PRIMARY KEY,
    sub_category VARCHAR(50) NOT NULL,
    category_id VARCHAR(50) NOT NULL
);

CREATE TABLE product_service.t_category (
    category_id VARCHAR(50) PRIMARY KEY,
    category VARCHAR(50) NOT NULL
);