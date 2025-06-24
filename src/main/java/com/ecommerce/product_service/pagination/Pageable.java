package com.ecommerce.product_service.pagination;

public interface Pageable {
    int getPageNumber();
    int getPageSize();

    static Pageable of(int pageNumber, int pageSize){
        return new Pageable() {
            @Override
            public int getPageNumber() {
                return pageNumber;
            }

            @Override
            public int getPageSize() {
                return pageSize;
            }
        };
    }
}
