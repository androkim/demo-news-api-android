package com.andronicuskim.newsapp.articledetails;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.andronicuskim.newsapp.SingleFragmentActivity;

public class ArticleDetailsActivity extends SingleFragmentActivity {

    public static final String URL = "url";

    public static Intent newIntent(Context context,String url){

        Intent intent = new Intent(context,ArticleDetailsActivity.class);
        intent.putExtra(URL,url);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        return ArticleDetailsFragment.newInstance(getIntent().getStringExtra(URL));
    }

}
