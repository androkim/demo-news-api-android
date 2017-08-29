package com.andronicuskim.newsapp.sources;

import com.andronicuskim.newsapp.utils.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class SourcesModule {
    private final SourcesContract.View mView;

    public SourcesModule(SourcesContract.View view) {
        mView = view;
    }

    @Provides
    @FragmentScope
    public SourcesContract.View provideView(){
        return mView;
    }
}
