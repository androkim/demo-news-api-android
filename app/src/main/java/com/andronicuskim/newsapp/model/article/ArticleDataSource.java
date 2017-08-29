package com.andronicuskim.newsapp.model.article;


import java.util.List;

public interface ArticleDataSource {

    void getArticles();

    void getSources();

    void getTechnologyArticles();

    List<Article> getListOfArticles();
}
