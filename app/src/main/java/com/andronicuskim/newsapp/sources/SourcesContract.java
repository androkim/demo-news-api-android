package com.andronicuskim.newsapp.sources;

import com.andronicuskim.newsapp.BasePresenter;
import com.andronicuskim.newsapp.BaseView;
import com.andronicuskim.newsapp.model.source.Source;

import java.util.List;

/**
 * Created by dataintegrated on 8/24/2017.
 */

public interface SourcesContract {
    interface View extends BaseView<Presenter>{

        void showSources(List<Source> sources);

        void showDialog();

        void hideDialog();

        void showLoadingError();
    }
    interface Presenter extends BasePresenter{

        void getSources();
    }
}
