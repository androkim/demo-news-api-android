package com.andronicuskim.newsapp.sources;


import com.andronicuskim.newsapp.NewsApplicationComponent;
import com.andronicuskim.newsapp.utils.FragmentScope;

import dagger.Component;
@FragmentScope
@Component(modules = SourcesModule.class,dependencies = NewsApplicationComponent.class)
public interface SourcesComponent {
    void inject(SourcesFragment fragment);
}
