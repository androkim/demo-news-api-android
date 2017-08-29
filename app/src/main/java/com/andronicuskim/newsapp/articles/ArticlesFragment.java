package com.andronicuskim.newsapp.articles;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andronicuskim.newsapp.NewsApplication;
import com.andronicuskim.newsapp.R;
import com.andronicuskim.newsapp.articledetails.ArticleDetailsActivity;
import com.andronicuskim.newsapp.model.NewsService;
import com.andronicuskim.newsapp.model.article.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesFragment extends Fragment implements ArticlesContract.View{

    private static final String TAG = "ArticlesFragment";

    public static final String CATEGORY = "category";

    @Inject
    NewsService mNewsService;

    @Inject
    Picasso mPicasso;

    @Inject
    ArticlesPresenter mArticlesPresenter;

    private ArticlesContract.Presenter mPresenter;

    private ArticlesAdapter mAdapter;

    private ProgressDialog mProgressDialog;

    @BindView(R.id.rv_articles)
    RecyclerView mRecyclerView;
    private String mCategory;

    public static ArticlesFragment newInstance(String category) {
        
        Bundle args = new Bundle();
        args.putString(CATEGORY,category);
        
        ArticlesFragment fragment = new ArticlesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mCategory = bundle.getString(CATEGORY);
        DaggerArticlesComponent.builder()
                .articlesPresenterModule(new ArticlesPresenterModule(this))
                .newsApplicationComponent(NewsApplication.get(getActivity()).getComponent())
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articles, container, false);
        ButterKnife.bind(this,view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void setPresenter(ArticlesContract.Presenter presenter) {
        if (mPresenter == null){
            mPresenter = presenter;
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        /*
        * Check the category in order to make appropriate presenter method call
        * */
        if (mCategory.equals("all")){
            mPresenter.subscribe();
        }else if(mCategory.equals("technology"))
        {
            /*
            *
            * Get Only technology related articles from a given source
            * */
            mPresenter.getTechnologyArticles();
        }
    }

    @Override
    public void showArticles(List<Article> articles) {
        /*
        * Check if fragment is already added to activity then create an instance of our adapter if we
        * don't have one already
        * */
        if (isAdded()){
                    if (mAdapter == null){
                        mAdapter = new ArticlesAdapter(articles);
                        mRecyclerView.setAdapter(mAdapter);
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

    public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesFragment.ArticlesViewHolder>{

        private List<Article> mArticles;

        public ArticlesAdapter(List<Article> articles) {
            mArticles = articles;
        }

        @Override
        public ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_article,parent,false);
            return new ArticlesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ArticlesViewHolder holder, int position) {
            Article article = mArticles.get(position);
            holder.bind(article);
        }

        @Override
        public int getItemCount() {
            return mArticles.size();
        }
    }
    public class ArticlesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tv_title)
        TextView mTextViewTitle;
        @BindView(R.id.tv_author)
        TextView mTextViewAuthor;
        @BindView(R.id.tv_published)
        TextView mTextViewPublished;
        @BindView(R.id.iv_url_image)
        ImageView mImageViewUrlImage;

        public ArticlesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(Article article){
            mTextViewTitle.setText(article.getTitle());
            mTextViewAuthor.setText(article.getAuthor());
            mTextViewPublished.setText(article.getPublishedAt());
            mPicasso.load(article.getUrlToImage()).into(mImageViewUrlImage);
        }

        @Override
        public void onClick(View v) {
            String url = mAdapter.mArticles.get(getAdapterPosition()).getUrl();
            startActivity(ArticleDetailsActivity.newIntent(getActivity(),url));
        }
    }

}
