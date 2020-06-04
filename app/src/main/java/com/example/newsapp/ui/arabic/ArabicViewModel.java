package com.example.newsapp.ui.arabic;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.models.Article;
import com.example.newsapp.requests.ServiceGenerator;
import com.example.newsapp.requests.responses.NewsApiResponse;
import com.example.newsapp.util.Constants;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ArabicViewModel extends ViewModel {

    private MutableLiveData<List<Article>> articleList;

    private static final String TAG = "ArabicViewModel";
    private CompositeDisposable compositeDisposable=new CompositeDisposable();


    public ArabicViewModel() {
        articleList = new MutableLiveData<>();
    }

    public LiveData<List<Article>> getArabic_news() {

        final Single<NewsApiResponse> single = ServiceGenerator.getArticleApi()
                .get_news_basesLanguage("ar", Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        SingleObserver<NewsApiResponse> observer = new SingleObserver<NewsApiResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: ");
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull NewsApiResponse newsApiResponse) {

                Log.d(TAG, "onNext: ");
                articleList.setValue((newsApiResponse.getArticles()));
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: " + e);

            }
        };
        single.subscribe(observer);

        return articleList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        compositeDisposable.clear();
        Log.d(TAG, "onCleared: ");
    }
}