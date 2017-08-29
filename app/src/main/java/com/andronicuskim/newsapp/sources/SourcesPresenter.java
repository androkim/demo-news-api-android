package com.andronicuskim.newsapp.sources;

import com.andronicuskim.newsapp.model.source.SourceRepository;

import javax.inject.Inject;

/**
 * Created by dataintegrated on 8/24/2017.
 */

public class SourcesPresenter implements SourcesContract.Presenter {

    private SourcesContract.View mView;
    private SourceRepository mSourceRepository;

    @Inject
    public SourcesPresenter(SourcesContract.View view, SourceRepository sourceRepository) {
        mView = view;
        mSourceRepository = sourceRepository;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        getSources();
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getSources() {
       mSourceRepository.getTechnologySources();
    }
}
