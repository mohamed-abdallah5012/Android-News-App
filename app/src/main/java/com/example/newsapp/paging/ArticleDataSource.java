package com.example.newsapp.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PageKeyedDataSource;

import com.example.newsapp.models.Article;
import com.example.newsapp.repositories.ArticleRepository;
import com.example.newsapp.requests.ArticleApi;
import com.example.newsapp.requests.ServiceGenerator;
import com.example.newsapp.requests.responses.NewsApiResponse;
import com.example.newsapp.ui.source.SourceViewModel;
import com.example.newsapp.util.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDataSource extends PageKeyedDataSource<Integer,Article> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Article> callback) {

        ArticleApi articleApi= ServiceGenerator.getArticleApi();
        Call<NewsApiResponse> responseCall =articleApi.getData(Constants.SEARCH_WORD,Constants.FIRST_PAGE,Constants.PAGE_SIZE, Constants.API_KEY);

        responseCall.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {


                NewsApiResponse newsApiResponse=response.body();

                if (response.code()==200)
                {
                    if(newsApiResponse !=null)
                    {
                        List<Article> article=newsApiResponse.getArticles();
                        callback.onResult(article,null,Constants.FIRST_PAGE+1 );

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

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Article> callback) {

        ArticleApi articleApi= ServiceGenerator.getArticleApi();
        Call<NewsApiResponse> responseCall =articleApi.getData(Constants.SEARCH_WORD,params.key,Constants.PAGE_SIZE, Constants.API_KEY);

        responseCall.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {

                NewsApiResponse newsApiResponse=response.body();

                if (response.code()==200)
                {
                    if(newsApiResponse !=null)
                    {
                        List<Article> article=newsApiResponse.getArticles();
                        Integer key;
                        if(params.key>1)
                            key=params.key-1;
                        else
                            key =null;
                        callback.onResult(article,key );
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
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Article> callback) {


//        ArticleApi articleApi= ServiceGenerator.getArticleApi();
//        Call<NewsApiResponse> responseCall =articleApi.getData(Constants.SEARCH_WORD,params.key,Constants.PAGE_SIZE, Constants.API_KEY);
//
//        responseCall.enqueue(new Callback<NewsApiResponse>() {
//            @Override
//            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
//
//                NewsApiResponse newsApiResponse=response.body();
//
//                if (response.code()==200)
//                {
//                    if(newsApiResponse !=null)
//                    {
//                        List<Article> article=newsApiResponse.getArticles();
//                        callback.onResult(article,params.key+1 );
//
//                    }
//                }
//                else
//                {
//                    response.errorBody().toString();
//                }
//            }
//            @Override
//            public void onFailure(Call<NewsApiResponse> call, Throwable t) {
//
//                t.getMessage().trim();
//            }
//        });



    }
}
