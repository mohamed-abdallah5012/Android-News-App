package com.example.newsapp.ui.country;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.adapters.NewsAdapter;
import com.example.newsapp.models.Article;
import com.example.newsapp.paging.NewsPageAdapter;
import com.example.newsapp.requests.ArticleApi;
import com.example.newsapp.requests.ServiceGenerator;
import com.example.newsapp.requests.responses.NewsApiResponse;
import com.example.newsapp.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryFragment extends Fragment {

    private CountryViewModel countryViewModel;
    //private NewsPageAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        countryViewModel =
                ViewModelProviders.of(this).get(CountryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_host, container, false);


        progressBar = root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = root.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        final NewsPageAdapter adapter = new NewsPageAdapter(getContext());

        ConnectivityManager manager = (ConnectivityManager) requireActivity().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo network = manager.getActiveNetworkInfo();
        if (network != null && network.isConnected()) {
            countryViewModel.getArticlePagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<Article>>() {
                @Override
                public void onChanged(PagedList<Article> articles) {
                    adapter.submitList(articles);
                    progressBar.setVisibility(View.GONE);
                }
            });

        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    requireActivity().finish();
                    Toast.makeText(getActivity(), "Check your connection and try again later", Toast.LENGTH_SHORT).show();
                }
            }, 1000);
        }
        recyclerView.setAdapter(adapter);


        return root;
    }


}