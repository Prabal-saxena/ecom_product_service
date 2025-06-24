package com.ecommerce.product_service.pagination;

import java.util.List;

public interface Page<T> extends Iterable<T> {
    List<T> getContent();
    int getNumber();
    int getSize();
    long getTotalElements();
    int getTotalPages();
    boolean hasNext();
    boolean hasPrevious();
    boolean isFirst();
    boolean isLast();
}
