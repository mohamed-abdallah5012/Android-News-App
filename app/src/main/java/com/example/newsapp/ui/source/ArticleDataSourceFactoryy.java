package com.example.newsapp.ui.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.newsapp.models.Article;

public class ArticleDataSourceFactoryy extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Article>> articleLiveDataSource=new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource create() {
        ArticleDataSourcee articleDataSourcee =new ArticleDataSourcee();
        articleLiveDataSource.postValue(articleDataSourcee);
        return articleDataSourcee;

    }

    public MutableLiveData<PageKeyedDataSource<Integer, Article>> getArticleLiveDataSource() {
        return articleLiveDataSource;
    }
}
