package com.example.newsapp.requests;
import com.example.newsapp.requests.responses.NewsApiResponse;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleApi {


    @GET("top-headlines")
    Single<NewsApiResponse> get_news_basesLanguage(
            @Query("language") String language,
            @Query("apiKey") String key
    );

    @GET("top-headlines")
    Observable<NewsApiResponse> get_news_basedCountry(
            @Query("country") String country,
            @Query("page") int page,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String key
    );

    @GET("everything")
    Observable<NewsApiResponse> getData(
            @Query("q") String q,
            @Query("page") int page,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String key
    );


    @GET("top-headlines")
    Observable<NewsApiResponse> getNewsCategory(
            @Query("category") String category,
            @Query("page") int page,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String key
    );

}
