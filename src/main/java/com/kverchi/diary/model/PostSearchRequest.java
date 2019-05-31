package com.kverchi.diary.model;

import com.kverchi.diary.model.enums.PostSearchingCriteria;
import com.kverchi.diary.model.enums.PostSortingCriteria;

import java.util.Map;

/**
 * Created by Kverchi on 18.7.2018.
 */
public class PostSearchRequest {
    private Map<PostSearchingCriteria, Object> searchAttributes;
    private Map<PostSortingCriteria, Object> sortAttributes;

    private int currentPage;
    private int pageSize;

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

    public Map<PostSearchingCriteria, Object> getSearchAttributes() {
        return searchAttributes;
    }

    public void setSearchAttributes(Map<PostSearchingCriteria, Object> searchAttributes) {
        this.searchAttributes = searchAttributes;
    }

    public Map<PostSortingCriteria, Object> getSortAttributes() {
        return sortAttributes;
    }

    public void setSortAttributes(Map<PostSortingCriteria, Object> sortAttributes) {
        this.sortAttributes = sortAttributes;
    }
}
