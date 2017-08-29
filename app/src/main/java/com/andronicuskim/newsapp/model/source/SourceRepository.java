package com.andronicuskim.newsapp.model.source;

import com.andronicuskim.newsapp.model.NewsService;
import com.andronicuskim.newsapp.model.RetrofitClient;
import com.andronicuskim.newsapp.sources.SourcesContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SourceRepository implements DataSource {


    private List<Source> mSources = new ArrayList<>();

    private SourcesContract.View mView;

    @Inject
    public SourceRepository(SourcesContract.View sourceView) {
        mView = sourceView;
    }

//    Get sources with technology category

    @Override
    public List<Source> getTechnologySources() {
        mView.showDialog();
        RetrofitClient client = new RetrofitClient();

        NewsService newsService = client.getNewsService();
        Call<SourcesResponseModel> call = newsService.getTechnologyCategory("technology");
        call.enqueue(new Callback<SourcesResponseModel>() {
            @Override
            public void onResponse(Call<SourcesResponseModel> call, Response<SourcesResponseModel> response) {
                mView.hideDialog();
                List<Source> sources = new ArrayList<>();
                for (Source source:response.body().getSources()){
                    sources.add(source);
                }
                mSources = sources;
                mView.showSources(sources);
            }

            @Override
            public void onFailure(Call<SourcesResponseModel> call, Throwable t) {

                mView.hideDialog();

                mView.showLoadingError();
            }
        });
        return mSources;
    }
}
