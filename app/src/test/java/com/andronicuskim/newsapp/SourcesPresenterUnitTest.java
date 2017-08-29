package com.andronicuskim.newsapp;

import com.andronicuskim.newsapp.model.source.Source;
import com.andronicuskim.newsapp.model.source.SourceRepository;
import com.andronicuskim.newsapp.sources.SourcesContract;
import com.andronicuskim.newsapp.sources.SourcesPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SourcesPresenterUnitTest {

    @Mock
     SourcesContract.View mView;

    @Mock
     SourceRepository mSourceRepository;

    SourcesPresenter mPresenter;

    private static final Source testSource = new Source();

    @Before
    public void setUpTest(){
        MockitoAnnotations.initMocks(this);
        mPresenter = new SourcesPresenter(mView,mSourceRepository);
    }
    @Test
    public void onGetSourcesSuccessful() throws Exception {

//        Set up any data we need for the test

        List<Source> sourceList = new ArrayList<>();
        sourceList.add(testSource);

//        Set up our Mocks to return the Data we want
        Mockito.when(mSourceRepository.getTechnologySources()).thenReturn(sourceList);

//        Call the method we are testing
        mPresenter.getSources();

//        Test presenters behaviour after receiving the data
        Mockito.verify(mView).showSources(sourceList);

    }
}
