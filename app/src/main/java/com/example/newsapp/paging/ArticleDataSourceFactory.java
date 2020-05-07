package com.example.newsapp.paging;

import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.newsapp.models.Article;

public class ArticleDataSourceFactory extends DataSource.Factory {


    private MutableLiveData<PageKeyedDataSource<Integer, Article>> articleLiveDataSource=new MutableLiveData<>();
    @NonNull
    @Override
    public DataSource create() {
        ArticleDataSource articleDataSource=new ArticleDataSource();
        articleLiveDataSource.postValue(articleDataSource);
        return articleDataSource;

    }

    public MutableLiveData<PageKeyedDataSource<Integer, Article>> getArticleLiveDataSource() {
        return articleLiveDataSource;
    }
}
