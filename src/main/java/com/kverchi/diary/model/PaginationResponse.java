package com.kverchi.diary.model;

import org.springframework.hateoas.Resources;

/**
 * Created by Liudmyla Melnychuk on 11.6.2019.
 */
public class PaginationResponse<T> {
    private Resources<T> resources;
    private int totalPages;

    private long totalElements;
    private int currentPage;
    private int pageSize;

    public PaginationResponse(int currentPage, int pageSize,
                              int totalPages, long totalElements) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public Resources<T> getResources() {
        return resources;
    }

    public void setResources(Resources<T> resources) {
        this.resources = resources;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
