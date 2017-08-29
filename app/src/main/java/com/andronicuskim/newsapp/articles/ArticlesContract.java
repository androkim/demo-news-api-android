package com.andronicuskim.newsapp.articles;

import com.andronicuskim.newsapp.BasePresenter;
import com.andronicuskim.newsapp.BaseView;
import com.andronicuskim.newsapp.model.article.Article;

import java.util.List;

public interface ArticlesContract {
    interface View extends BaseView<Presenter>{

        void showArticles(List<Article> articles);

        void showDialog();

        void hideDialog();

        void showLoadingError();

    }
    interface Presenter extends BasePresenter{
        void getArticles();

        void getTechnologyArticles();
    }
}
