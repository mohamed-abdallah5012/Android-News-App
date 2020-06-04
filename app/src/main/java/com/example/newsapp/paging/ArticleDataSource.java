//package com.example.newsapp.paging;
//
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.paging.PageKeyedDataSource;
//
//import com.example.newsapp.models.Article;
//import com.example.newsapp.requests.ServiceGenerator;
//import com.example.newsapp.requests.responses.NewsApiResponse;
//import com.example.newsapp.util.Constants;
//
//import java.util.List;
//
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
//import io.reactivex.rxjava3.core.Observable;
//import io.reactivex.rxjava3.core.Observer;
//import io.reactivex.rxjava3.disposables.Disposable;
//import io.reactivex.rxjava3.schedulers.Schedulers;
//
//public class ArticleDataSource extends PageKeyedDataSource<Integer,Article> {
//
//    private static final String TAG = "ArticleDataSource";
//    @Override
//    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Article> callback) {
//
//        Observable<NewsApiResponse> observable=ServiceGenerator.getArticleApi()
//                .getData(Constants.SEARCH_WORD,Constants.FIRST_PAGE,Constants.PAGE_SIZE, Constants.API_KEY)
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//        Observer<NewsApiResponse> observer=new Observer<NewsApiResponse>() {
//            @Override
//            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
//                Log.d(TAG, "onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(@io.reactivex.rxjava3.annotations.NonNull NewsApiResponse newsApiResponse) {
//
//                Log.d(TAG, "onNext: ");
//                if(newsApiResponse !=null)
//                {
//                    List<Article> article=newsApiResponse.getArticles();
//                    callback.onResult(article,null,Constants.FIRST_PAGE+1 );
//                }
//            }
//
//            @Override
//            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
//
//                Log.d(TAG, "onError: "+e);
//            }
//
//            @Override
//            public void onComplete() {
//
//                Log.d(TAG, "onComplete: ");;
//            }
//        };
//
//
//        observable.subscribe(observer);
//    }
//
//    @Override
//    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Article> callback) {
//
//
//
//
//        Observable<NewsApiResponse> observable=ServiceGenerator.getArticleApi()
//                .getData(Constants.SEARCH_WORD,params.key,Constants.PAGE_SIZE, Constants.API_KEY)
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//        Observer<NewsApiResponse> observer=new Observer<NewsApiResponse>() {
//            @Override
//            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
//                Log.d(TAG, "onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(@io.reactivex.rxjava3.annotations.NonNull NewsApiResponse newsApiResponse) {
//
//                Log.d(TAG, "onNext: ");
//                if(newsApiResponse !=null)
//                {
//                    List<Article> article=newsApiResponse.getArticles();
//                        Integer key;
//                        if(params.key>1)
//                            key=params.key-1;
//                        else
//                            key =null;
//                        callback.onResult(article,key );
//                }
//            }
//
//            @Override
//            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
//
//                Log.d(TAG, "onError: "+e);
//            }
//
//            @Override
//            public void onComplete() {
//
//                Log.d(TAG, "onComplete: ");;
//            }
//        };
//
//
//        observable.subscribe(observer);
//
//    }
//
//    @Override
//    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Article> callback) {
//
//
//
//        Observable<NewsApiResponse> observable=ServiceGenerator.getArticleApi()
//                .getData(Constants.SEARCH_WORD,params.key,Constants.PAGE_SIZE, Constants.API_KEY)
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//        Observer<NewsApiResponse> observer=new Observer<NewsApiResponse>() {
//            @Override
//            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
//                Log.d(TAG, "onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(@io.reactivex.rxjava3.annotations.NonNull NewsApiResponse newsApiResponse) {
//
//                Log.d(TAG, "onNext: ");
//                if(newsApiResponse !=null)
//                {
//                    if(newsApiResponse !=null)
//                    {
//                        List<Article> article=newsApiResponse.getArticles();
//                        callback.onResult(article,params.key+1 );
//
//                    }
//
//                }
//            }
//
//            @Override
//            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
//
//                Log.d(TAG, "onError: "+e);
//            }
//
//            @Override
//            public void onComplete() {
//
//                Log.d(TAG, "onComplete: ");;
//            }
//        };
//
//        observable.subscribe(observer);
//
//    }
//}
