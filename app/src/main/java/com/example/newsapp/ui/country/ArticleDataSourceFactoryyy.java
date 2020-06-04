package com.example.newsapp.ui.country;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.newsapp.models.Article;

public class ArticleDataSourceFactoryyy extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Article>> articleLiveDataSource=new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource create() {
        ArticleDataSourceee articleDataSourceee =new ArticleDataSourceee();
        articleLiveDataSource.postValue(articleDataSourceee);
        return articleDataSourceee;

    }

    public MutableLiveData<PageKeyedDataSource<Integer, Article>> getArticleLiveDataSource() {
        return articleLiveDataSource;
    }
}
