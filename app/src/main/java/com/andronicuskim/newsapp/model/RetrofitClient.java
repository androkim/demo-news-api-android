package com.andronicuskim.newsapp.model;


import com.andronicuskim.newsapp.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class RetrofitClient {


    public RetrofitClient( ) {

    }

    //    (Contingency) since Dagger could not provide dependencies
    public NewsService getNewsService() {
        return getApiClient().create(NewsService.class);
    }

    public Retrofit getApiClient() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient(getInterceptor()))
                .baseUrl(Constants.BASE_URL)
                .build();

    }

    public HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return interceptor;

    }

    public OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

}
