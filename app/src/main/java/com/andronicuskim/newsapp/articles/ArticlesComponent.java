package com.andronicuskim.newsapp.articles;


import com.andronicuskim.newsapp.NewsApplicationComponent;
import com.andronicuskim.newsapp.utils.FragmentScope;

import dagger.Component;
@FragmentScope
@Component(modules = ArticlesPresenterModule.class, dependencies = NewsApplicationComponent.class)
public interface ArticlesComponent {
    void inject(ArticlesFragment fragment);
}
