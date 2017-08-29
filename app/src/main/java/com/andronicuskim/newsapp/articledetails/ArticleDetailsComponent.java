package com.andronicuskim.newsapp.articledetails;


import com.andronicuskim.newsapp.NewsApplicationComponent;
import com.andronicuskim.newsapp.utils.FragmentScope;

import dagger.Component;
@FragmentScope
@Component(modules = ArticleDetailsModule.class,dependencies = NewsApplicationComponent.class)
public interface ArticleDetailsComponent {
}
