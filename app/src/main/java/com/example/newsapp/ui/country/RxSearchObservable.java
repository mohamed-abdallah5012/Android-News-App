package com.example.newsapp.ui.country;

import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.example.newsapp.models.Article;
import com.example.newsapp.util.Constants;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class RxSearchObservable {

    private RxSearchObservable() {
        // no instance
    }

    public static Observable<String> fromView(SearchView searchView) {

        final PublishSubject<String> subject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                subject.onNext(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                subject.onNext(text);
                return true;
            }
        });

        return subject;
    }
}




//RXSearch.fromView(searchView)
//                .debounce(2000, TimeUnit.MILLISECONDS)
//                .filter(item -> item.length() > 1)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(query -> {
//                    Constants.SEARCH_WORD=query;
//                    Log.d(TAG, "onCreateView: "+Constants.SEARCH_WORD);
//                    countryViewModel.getArticlePagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<Article>>() {
//                        @Override
//                        public void onChanged(PagedList<Article> articles) {
//                            adapter.submitList(articles);
//                            progressBar.setVisibility(View.GONE);
//                            adapter.notifyDataSetChanged();
//                            recyclerView.setAdapter(adapter);
//                        }
//                    });
//                });
//



/*

private void setUpSearchObservable() {
        RxSearchObservable.fromView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String text) {
                        if (text.isEmpty()) {
                           // textViewResult.setText("");
                            Log.d(TAG, "test: "+text);
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String result) {
                        //textViewResult.setText(result);
                        Log.d(TAG, "test: "+result);


                        getData(result);




                    }
                });
    }

    /**
     * Simulation of network data
     */

/*
private Observable getData(final String query)
{
    Constants.SEARCH_WORD=query;
    Log.d(TAG, "getData: "+Constants.SEARCH_WORD);
    Observable observable= Observable.just(true)
            .delay(2, TimeUnit.SECONDS);
//                .map(new Function<Boolean, NewsApiResponse>() {
////                    @Override
////                    public NewsApiResponse apply(Boolean aBoolean) throws Throwable {
////                        return NewsApiResponse;
////                    }
////                });

    countryViewModel.getArticlePagedList()
            .observe(getViewLifecycleOwner(), new Observer<PagedList<Article>>() {
                @Override
                public void onChanged(PagedList<Article> articles) {
                    //articles.clear();
                    adapter.submitList(articles);
                    Log.d(TAG, "onChanged: ");
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            });


    return observable;




}
 */