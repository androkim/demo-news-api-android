package com.andronicuskim.newsapp.articles;


import com.andronicuskim.newsapp.utils.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ArticlesPresenterModule {

    private final ArticlesContract.View mView;

    public ArticlesPresenterModule(ArticlesContract.View view) {
        mView = view;
    }

    @Provides
    @FragmentScope
    public ArticlesContract.View provideView(){
        return mView;
    }
}
