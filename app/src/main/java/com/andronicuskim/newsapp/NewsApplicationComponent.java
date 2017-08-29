package com.andronicuskim.newsapp;

import com.andronicuskim.newsapp.model.NewsService;
import com.squareup.picasso.Picasso;

import dagger.Component;
@NewsApplicationScope
@Component(modules = {NewsApplicationModule.class})
public interface NewsApplicationComponent {
    NewsService newsService();

    Picasso picasso();
}
