package com.andronicuskim.newsapp.articles;


import com.andronicuskim.newsapp.model.article.ArticleRepository;

import javax.inject.Inject;

public class ArticlesPresenter implements ArticlesContract.Presenter {

    private final ArticlesContract.View mView;
    private final ArticleRepository mRepository;

    @Inject
    public ArticlesPresenter(ArticlesContract.View view,
                             ArticleRepository articleRepository) {
        mView = view;
        mRepository = articleRepository;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }
    @Override
    public void subscribe() {
        getArticles();
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getArticles() {
        mRepository.getArticles();

    }

    @Override
    public void getTechnologyArticles() {

        mRepository.getTechnologyArticles();
    }
}
