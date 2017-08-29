package com.andronicuskim.newsapp.model.article;

import com.andronicuskim.newsapp.model.source.Source;


public class ArticleRequestModel {

    private Source mSource;

    private String sortBy;

    private String apiKey;

    public Source getSource() {
        return mSource;
    }

    public void setSource(Source source) {
        mSource = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
