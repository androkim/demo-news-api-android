package com.andronicuskim.newsapp.sources;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.andronicuskim.newsapp.NewsApplication;
import com.andronicuskim.newsapp.R;
import com.andronicuskim.newsapp.model.source.Source;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SourcesFragment extends Fragment implements SourcesContract.View{

    private static final String TAG = "SourcesFragment";

    private SourcesAdapter mSourcesAdapter;

    private ProgressDialog mProgressDialog;

    @BindView(R.id.rv_sources)
    RecyclerView mRecyclerView;

    @Inject
    SourcesPresenter mSourcesPresenter;

    private SourcesContract.Presenter mPresenter;

    public SourcesFragment() {
        // Required empty public constructor
    }

    public static SourcesFragment newInstance() {

        Bundle args = new Bundle();

        SourcesFragment fragment = new SourcesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerSourcesComponent.builder()
                .sourcesModule(new SourcesModule(this))
                .newsApplicationComponent(NewsApplication.get(getActivity()).getComponent())
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sources, container, false);
        ButterKnife.bind(this,view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void setPresenter(SourcesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
       mPresenter.subscribe();
    }

    @Override
    public void showSources(List<Source> sources) {

        if (isAdded()){
            if (mSourcesAdapter == null){
                mSourcesAdapter = new SourcesAdapter(sources);
                mRecyclerView.setAdapter(mSourcesAdapter);
            }
        }

    }

    @Override
    public void showDialog() {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading. Please Wait...");
        mProgressDialog.show();
    }

    @Override
    public void hideDialog() {

        mProgressDialog.dismiss();
    }

    @Override
    public void showLoadingError() {
        Toast.makeText(getActivity(), "An Error occurred!", Toast.LENGTH_SHORT).show();
    }

    public class SourcesAdapter extends RecyclerView.Adapter<SourcesViewHolder>{

        List<Source> mSources;

        public SourcesAdapter(List<Source> sources) {
            mSources = sources;
        }

        @Override
        public SourcesViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_source,parent,false);
            return new SourcesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SourcesViewHolder holder, int position) {

            Source source = mSources.get(position);
            holder.bind(source);
        }

        @Override
        public int getItemCount() {
            return mSources.size();
        }
    }
    public class SourcesViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_source_name)
        TextView mTextViewName;
        @BindView(R.id.tv_source_description)
        TextView mTextViewDescription;
        @BindView(R.id.tv_source_url)
        TextView mTextViewUrl;

        public SourcesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(Source source){
            mTextViewName.setText(source.getName());
            mTextViewDescription.setText(source.getDescription());
            mTextViewUrl.setText(source.getUrl());
        }
    }
}
