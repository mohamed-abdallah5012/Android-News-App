package com.example.newsapp.ui.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.models.Article;
import com.example.newsapp.repositories.ArticleRepository;

import java.util.List;

public class SourceViewModel extends ViewModel {

    private LiveData<List<Article>> articleList;
    private ArticleRepository repository;
    public static SourceViewModel getInstance()
    {
        return new SourceViewModel();
    }


    public SourceViewModel() {
        repository=ArticleRepository.getInstance();
        articleList=new MutableLiveData<>();
    }

    public LiveData<List<Article>> getData(Integer page)
    {
        articleList= repository.getData(page);
        return articleList;
    }}