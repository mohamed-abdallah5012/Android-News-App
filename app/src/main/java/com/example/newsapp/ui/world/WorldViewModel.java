package com.example.newsapp.ui.world;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.models.Article;
import com.example.newsapp.repositories.ArticleRepository;

import java.util.List;

public class WorldViewModel extends ViewModel {

    private LiveData<List<Article>> articleList;
    private ArticleRepository repository;


    public WorldViewModel() {
        repository=ArticleRepository.getInstance();
        articleList=new MutableLiveData<>();
        articleList=repository.getWorld_news();
    }

    public LiveData<List<Article>> getWorld_news()
    {
        articleList= repository.getWorld_news();
        return articleList;
    }}