package com.kverchi.diary.model;

import com.kverchi.diary.model.enums.PostSearchCriteria;

import java.util.Map;

/**
 * Created by Kverchi on 18.7.2018.
 */
public class PostSearchRequest {
    private Map<PostSearchCriteria, Object> searchAttributes;
    private int currentPage;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Map<PostSearchCriteria, Object> getSearchAttributes() {
        return searchAttributes;
    }

    public void setSearchAttributes(Map<PostSearchCriteria, Object> searchAttributes) {
        this.searchAttributes = searchAttributes;
    }
}
