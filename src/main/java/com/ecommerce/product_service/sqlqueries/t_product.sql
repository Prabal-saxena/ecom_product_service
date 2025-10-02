
CREATE TABLE product_service.t_product (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    price NUMERIC(10, 2) NOT NULL, -- Recommended type for BigDecimal
    image_url VARCHAR(100) NOT NULL,
    category_id VARCHAR(50) NOT NULL, -- Assuming foreign key to Category table
    sub_category_id VARCHAR(50) NOT NULL, -- Assuming foreign key to SubCategory table
    quantity INTEGER NOT NULL,
    rating INTEGER NOT NULL,
    country VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    alcohol_vol REAL NOT NULL,
	creator VARCHAR(255) NOT NULL,
    tags TEXT[] NOT NULL
);