package com.example.newsapp.requests;

import com.example.newsapp.requests.responses.NewsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleApi {




    @GET("top-headlines")
    Call <NewsApiResponse> getWorld_news(
            @Query("language") String language,
            @Query("apiKey") String key
    );

    @GET("top-headlines")
    Call <NewsApiResponse> getArabic_news(
            @Query("language") String language,
            @Query("apiKey") String key
    );

    @GET("everything")
    Call<NewsApiResponse> getData(
            @Query("q") String q,
            @Query("page") int page,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String key
    );





}
