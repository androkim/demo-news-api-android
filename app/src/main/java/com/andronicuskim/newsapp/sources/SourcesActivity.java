package com.andronicuskim.newsapp.sources;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.andronicuskim.newsapp.SingleFragmentActivity;

public class SourcesActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,SourcesActivity.class);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        return SourcesFragment.newInstance();
    }
}
