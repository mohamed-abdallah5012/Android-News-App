package com.example.newsapp.ui.arabic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.models.Article;
import com.example.newsapp.repositories.ArticleRepository;

import java.util.List;

public class ArabicViewModel extends ViewModel {

    private LiveData<List<Article>> articleList;
    private ArticleRepository repository;


    public ArabicViewModel() {
        repository=ArticleRepository.getInstance();
        articleList=new MutableLiveData<>();
        articleList=repository.getArabic_news();
    }

    public LiveData<List<Article>> getArabic_news()
    {
        articleList= repository.getArabic_news();
        return articleList;
    }}