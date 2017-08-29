package com.andronicuskim.newsapp.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.andronicuskim.newsapp.R;
import com.andronicuskim.newsapp.articles.ArticlesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginFragment extends Fragment {

    @BindView(R.id.et_email)
    EditText mEditTextEmail;

    @BindView(R.id.et_password)
    EditText mEditTextPassword;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @OnClick(R.id.btn_login)
    public void login(){
        String email = mEditTextEmail.getText().toString().trim();
        String password = mEditTextPassword.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getActivity(), "Login Fields Empty!", Toast.LENGTH_SHORT).show();
        }else if (!email.matches(emailPattern)&& !password.isEmpty()){
            Toast.makeText(getActivity(), "Invalid email address", Toast.LENGTH_SHORT).show();

        }else if (email.matches(emailPattern) && !password.isEmpty()){
            startActivity(ArticlesActivity.newIntent(getActivity(),"all"));
        }
    }

}
