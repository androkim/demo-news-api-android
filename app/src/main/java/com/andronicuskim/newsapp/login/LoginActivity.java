package com.andronicuskim.newsapp.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.andronicuskim.newsapp.R;
import com.andronicuskim.newsapp.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.app_name);

       LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
       if (fragment == null){
           fragment = LoginFragment.newInstance();
           ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.fragment_container);
       }
    }
}
