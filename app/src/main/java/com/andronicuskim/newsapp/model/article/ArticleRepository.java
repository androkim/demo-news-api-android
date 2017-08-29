package com.andronicuskim.newsapp.model.article;


import com.andronicuskim.newsapp.articles.ArticlesContract;
import com.andronicuskim.newsapp.model.NewsService;
import com.andronicuskim.newsapp.model.source.Source;
import com.andronicuskim.newsapp.model.source.SourcesResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository implements ArticleDataSource {

    private List<Article> mArticles;

    private ArticlesContract.View mView;

    private NewsService mNewsService;

    @Inject
    public ArticleRepository(ArticlesContract.View view,NewsService newsService) {

        mView = view;

        mNewsService = newsService;

    }

    @Override
    public void getArticles() {
        mView.showDialog();
//        RetrofitClient client = new RetrofitClient();

//        NewsService newsService = client.getNewsService();
        Call<ArticlesResponseModel> call = mNewsService.getArticles("the-next-web",
                "latest",
                "c01662d6f0d642d2ae0f233189a5f742");
        call.enqueue(new Callback<ArticlesResponseModel>() {
            @Override
            public void onResponse(Call<ArticlesResponseModel> call, Response<ArticlesResponseModel> response) {
                mView.hideDialog();
                List<Article> articles = new ArrayList<>();
                try {
                    for (Article article:response.body().getArticles()){
                        articles.add(article);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mArticles = articles;
                mView.showArticles(articles);
            }

            @Override
            public void onFailure(Call<ArticlesResponseModel> call, Throwable t) {

                mView.hideDialog();
                mView.showLoadingError();
            }
        });
    }
//    Get only technology articles
    @Override
    public void getTechnologyArticles() {
//        First get a list of sources with technology category
        getSources();

    }
    @Override
    public void getSources() {
        mView.showDialog();
//        RetrofitClient client = new RetrofitClient();
//
//        NewsService newsService = client.getNewsService();
        Call<SourcesResponseModel> call = mNewsService.getTechnologyCategory("technology");
        call.enqueue(new Callback<SourcesResponseModel>() {
            @Override
            public void onResponse(Call<SourcesResponseModel> call, Response<SourcesResponseModel> response) {
                List<Source> sources = new ArrayList<>();
                for (Source source:response.body().getSources()){
                    sources.add(source);
                }
//                pass the list to makeCall method for it to generate random source Id

                makeCall(sources);
            }

            @Override
            public void onFailure(Call<SourcesResponseModel> call, Throwable t) {
                mView.hideDialog();
                mView.showLoadingError();

            }
        });
    }

    private void makeCall(List<Source> sources){
//        Generate Random source Id(source)
        Random random = new Random();
        String source = sources.get(random.nextInt(sources.size())).getId();

//        RetrofitClient client = new RetrofitClient();
//
//        NewsService newsService = client.getNewsService();
        Call<ArticlesResponseModel> call = mNewsService.getTechnologyArticles(source,
                "latest",
                "c01662d6f0d642d2ae0f233189a5f742");
        call.enqueue(new Callback<ArticlesResponseModel>() {
            @Override
            public void onResponse(Call<ArticlesResponseModel> call, Response<ArticlesResponseModel> response) {
                mView.hideDialog();
                List<Article> articles = new ArrayList<>();
                try {
                    for (Article article:response.body().getArticles()){
                        articles.add(article);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mArticles = articles;
                mView.showArticles(articles);
            }

            @Override
            public void onFailure(Call<ArticlesResponseModel> call, Throwable t) {

                mView.hideDialog();

                mView.showLoadingError();

            }
        });
    }

    @Override
    public List<Article> getListOfArticles() {
        if (mArticles == null){
            mArticles = new ArrayList<>();
            return mArticles;
        }
        return mArticles;
    }
}
