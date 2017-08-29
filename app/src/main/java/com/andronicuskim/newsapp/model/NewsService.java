package com.andronicuskim.newsapp.model;

import com.andronicuskim.newsapp.model.article.ArticlesResponseModel;
import com.andronicuskim.newsapp.model.source.SourcesResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("articles")
    Call<ArticlesResponseModel> getArticles(@Query("source") String source,
                                            @Query("sortBy") String sortBy,
                                            @Query("apiKey") String apiKey);

    @GET("sources")
    Call<SourcesResponseModel> getTechnologyCategory(@Query("category") String category);

    @GET("articles")
    Call<ArticlesResponseModel> getTechnologyArticles(@Query("source") String source,
                                                      @Query("sortBy") String sortBy,
                                                      @Query("apiKey") String apiKey);
}
