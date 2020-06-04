package com.example.newsapp.ui.country;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.newsapp.models.Article;
import com.example.newsapp.util.Constants;

public class CountryViewModel extends ViewModel {

   private LiveData articlePagedList;
    private LiveData<PageKeyedDataSource<Integer, Article>> liveDataSource;

    //constructor
    public CountryViewModel() {
        //getting our data source factory
        ArticleDataSourceFactoryyy itemDataSourceFactory = new ArticleDataSourceFactoryyy();

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