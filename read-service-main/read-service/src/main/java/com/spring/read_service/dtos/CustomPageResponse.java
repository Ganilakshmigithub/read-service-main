package com.spring.read_service.dtos;

import java.util.List;
public class CustomPageResponse<T> {
    private List<T> content;
    private int totalPages;
    private int size;
    private long totalElements;

    public CustomPageResponse(List<T> content, int totalPages, int size,long totalElements) {
        this.content = content;
        this.totalPages = totalPages;
        this.size = size;
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public long getTotalElements() {
        return totalElements;
    }
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}






