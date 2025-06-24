package com.ecommerce.product_service.dto;

import com.ecommerce.product_service.pagination.Page;
import com.ecommerce.product_service.pagination.Pageable;

import java.util.Iterator;
import java.util.List;

public class PageImpl<T> implements Page<T> {
    private final List<T> content;
    private final int pageNumber;
    private final int pageSize;
    private final long totalElements;

    public PageImpl(List<T> content, Pageable pageable, long totalElements){
        this.content = content;
        this.pageNumber = pageable.getPageNumber();
        this.pageSize = pageable.getPageSize();
        this.totalElements = totalElements;
    }

    @Override
    public List<T> getContent() {
        return content;
    }

    @Override
    public int getNumber() {
        return pageNumber;
    }

    @Override
    public int getSize() {
        return pageSize;
    }

    @Override
    public long getTotalElements() {
        return totalElements;
    }

    @Override
    public int getTotalPages() {
        return (int) Math.ceil((double) totalElements / pageSize);
    }

    @Override
    public boolean hasNext() {
        return getNumber() < getTotalPages() - 1;
    }

    @Override
    public boolean hasPrevious() {
        return getNumber() > 0;
    }

    @Override
    public boolean isFirst() {
        return getNumber() == 0;
    }

    @Override
    public boolean isLast() {
        return getNumber() >= getTotalPages() - 1;
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }
}
