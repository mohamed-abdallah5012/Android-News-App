package com.example.newsapp.requests;



import com.example.newsapp.util.Constants;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            ;

    private static Retrofit retrofit = retrofitBuilder.build();

    private static ArticleApi articleApi = retrofit.create(ArticleApi.class);

    public static ArticleApi getArticleApi(){
        return articleApi;
    }
}