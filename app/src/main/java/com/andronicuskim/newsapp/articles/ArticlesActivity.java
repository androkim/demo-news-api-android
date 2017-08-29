package com.andronicuskim.newsapp.articles;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.andronicuskim.newsapp.SingleFragmentActivity;

public class ArticlesActivity extends SingleFragmentActivity {

    public static final String CATEGORY = "category";

    public static Intent newIntent(Context context,String category){
        Intent intent = new Intent(context,ArticlesActivity.class);
        intent.putExtra(CATEGORY,category);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        return ArticlesFragment.newInstance(getIntent().getStringExtra(CATEGORY));
    }
}
