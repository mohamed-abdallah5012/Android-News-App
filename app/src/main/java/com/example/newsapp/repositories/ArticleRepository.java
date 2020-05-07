package com.example.newsapp.repositories;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.models.Article;
import com.example.newsapp.requests.ArticleApi;
import com.example.newsapp.requests.ServiceGenerator;
import com.example.newsapp.requests.responses.NewsApiResponse;
import com.example.newsapp.util.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

    private static ArticleRepository instance;
    private  MutableLiveData<List<Article>> articleList;
    private  MutableLiveData<NewsApiResponse> arabicResponseMutableLiveData;

    private  MutableLiveData<List<Article>> arr;
    public static ArticleRepository getInstance() {
        if (instance == null) {
            instance = new ArticleRepository();
        }
        return instance;
    }
    public MutableLiveData<List<Article>> getData(Integer page)
    {
        arr=new MutableLiveData<>();
        ArticleApi articleApi= ServiceGenerator.getArticleApi();
        Call<NewsApiResponse> responseCall =articleApi.getData(Constants.SEARCH_WORD,page,Constants.PAGE_SIZE,Constants.API_KEY);

        responseCall.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response)
            {

                NewsApiResponse newsApiResponse=response.body();

                if (response.code()==200)
                {
                    if(newsApiResponse !=null)
                    {
                       arr.setValue(newsApiResponse.getArticles());
                    }
                }
                else
                {
                    response.errorBody().toString();
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {

                t.getMessage().trim();
            }
        });
        return arr;
    }

    public MutableLiveData<List<Article>> getWorld_news()
    {
            articleList=new MutableLiveData<>();
            arabicResponseMutableLiveData=new MutableLiveData<>();
            loadWorldNews();
            return articleList;
    }

    public MutableLiveData<List<Article>> getArabic_news()
    {
            articleList=new MutableLiveData<>();
            arabicResponseMutableLiveData=new MutableLiveData<>();
            loadArabicNews();
            return articleList;
    }

    public void loadWorldNews() {
        ArticleApi articleApi= ServiceGenerator.getArticleApi();
        Call<NewsApiResponse> responseCall =articleApi.getWorld_news("en", Constants.API_KEY);

        responseCall.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {

                if (response.code()==200)
                {
                    arabicResponseMutableLiveData.setValue(response.body());
                    articleList.setValue( arabicResponseMutableLiveData.getValue().getArticles());
                }
                else
                {
                    response.errorBody().toString();
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {

                t.getMessage().trim();
            }
        });


    }

    public void loadArabicNews() {
        ArticleApi articleApi= ServiceGenerator.getArticleApi();
        Call<NewsApiResponse> responseCall =articleApi.getArabic_news("ar",Constants.API_KEY);

        responseCall.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {


                if (response.code()==200)
                {
                    arabicResponseMutableLiveData.setValue(response.body());
                    articleList.setValue(( arabicResponseMutableLiveData.getValue().getArticles()));
                }
                else
                {
                    response.errorBody().toString();
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {

                t.getMessage().trim();
            }
        });



    }


}
