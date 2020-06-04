package com.example.newsapp.ui.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.newsapp.models.Article;
import com.example.newsapp.util.Constants;

import java.util.List;

public class SourceViewModel extends ViewModel {

    private LiveData articlePagedList;
    private LiveData<PageKeyedDataSource<Integer, Article>> liveDataSource;

    //constructor
    public SourceViewModel() {
        //getting our data source factory
        ArticleDataSourceFactoryy itemDataSourceFactory = new ArticleDataSourceFactoryy();

        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.getArticleLiveDataSource();

        //Getting PagedList config
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(Constants.PAGE_SIZE)
                        .build();

        //Building the paged list
        articlePagedList = (new LivePagedListBuilder<>(itemDataSourceFactory, pagedListConfig)).build();
    }

    public LiveData<PagedList<Article>> getArticlePagedList()
    {
        return articlePagedList;
    }
}