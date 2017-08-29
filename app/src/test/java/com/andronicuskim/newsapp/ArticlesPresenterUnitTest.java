package com.andronicuskim.newsapp;

import com.andronicuskim.newsapp.articles.ArticlesContract;
import com.andronicuskim.newsapp.articles.ArticlesPresenter;
import com.andronicuskim.newsapp.model.article.Article;
import com.andronicuskim.newsapp.model.article.ArticleRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ArticlesPresenterUnitTest {

    /*
    * This unit test is geared towards testing the logic of the app, therefore we won't
    * need real implementations of the views and datasource hence we mock them
    * */

    @Mock
    ArticlesContract.View mView;

    @Mock
    ArticleRepository mRepository;

    ArticlesPresenter mPresenter;

    private static final Article testArticle = new Article();

    @Before
    public void setUpTest(){
        MockitoAnnotations.initMocks(this);
       mPresenter = new ArticlesPresenter(mView,mRepository);
    }

    @Test
    public void onGetArticlesSuccessful() throws Exception {
//        Set up any data we need for the test
        List<Article> articles = new ArrayList<>();
        articles.add(testArticle);

//        Set up our Mocks to return the Data we want
        Mockito.when(mRepository.getListOfArticles()).thenReturn(articles);

//        Call the method we are testing
        mPresenter.getArticles();

//        Test presenters behaviour after receiving the data
        Mockito.verify(mView).showArticles(articles);

    }
}