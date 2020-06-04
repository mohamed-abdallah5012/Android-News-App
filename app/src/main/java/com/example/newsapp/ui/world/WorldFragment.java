package com.example.newsapp.ui.world;

import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.adapters.NewsAdapter;
import com.example.newsapp.models.Article;
import com.example.newsapp.util.Constants;

import java.util.Arrays;
import java.util.List;

public class WorldFragment extends Fragment {

    private WorldViewModel worldViewModel;
    private NewsAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        worldViewModel =
                ViewModelProviders.of(this).get(WorldViewModel.class);
        View root = inflater.inflate(R.layout.fragment_host, container, false);


        progressBar = root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = root.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setItemViewCacheSize(100);

        ConnectivityManager manager = (ConnectivityManager) requireActivity().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo network = manager.getActiveNetworkInfo();
        if (network != null && network.isConnected()) {
            getData();

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
        return root;
    }
    private void getData()
    {
        worldViewModel.getWorld_news().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {

                adapter = new NewsAdapter(getContext(), articles);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);

            }
        });
    }

}