package com.andronicuskim.newsapp;

import android.content.Context;

import com.andronicuskim.newsapp.model.NewsService;
import com.andronicuskim.newsapp.utils.Constants;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NewsApplicationModule {

    private final Context mContext;

    public NewsApplicationModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @NewsApplicationScope
    public Context context(){
        return mContext;
    }

    @Provides
    @NewsApplicationScope
    public NewsService getNewsService(Retrofit retrofit){
        return retrofit.create(NewsService.class);
    }

    @Provides
    @NewsApplicationScope
    public Retrofit getRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(Constants.BASE_URL)
                .build();
    }
    @Provides
    @NewsApplicationScope
    public HttpLoggingInterceptor getInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return interceptor;

    }
    @Provides
    @NewsApplicationScope
    public File file(@ApplicationContext Context context){
        return new File(context.getCacheDir(),"okhttp cache");
    }

    @Provides
    @NewsApplicationScope
    public Cache getCache(File cacheFile){
        return new Cache(cacheFile,10*1000*1000); //10MB cache
    }

    @Provides
    @NewsApplicationScope
    public OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor, Cache cache){
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }
    @Provides
    @NewsApplicationScope
    public Picasso picasso( Context context, OkHttp3Downloader downloader){
        return new Picasso.Builder(context)
                .downloader(downloader)
                .build();
    }
    @Provides
    @NewsApplicationScope
    public OkHttp3Downloader downloader(OkHttpClient client){
        return new OkHttp3Downloader(client);
    }
}
