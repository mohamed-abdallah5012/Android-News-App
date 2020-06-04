package com.example.newsapp.ui.source;

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
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.adapters.NewsAdapter;
import com.example.newsapp.models.Article;
import com.example.newsapp.util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

public class SourceFragment extends Fragment {

    private SourceViewModel sourceViewModel;
    private NewsPageAdapterr adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sourceViewModel =
                ViewModelProviders.of(this).get(SourceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_host, container, false);
        progressBar = root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = root.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setItemViewCacheSize(100);
        showCategory();

        return root;
    }
    private void getData()
    {
        adapter = new NewsPageAdapterr(getContext());

        ConnectivityManager manager = (ConnectivityManager) requireActivity().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo network = manager.getActiveNetworkInfo();
        if (network != null && network.isConnected()) {
            sourceViewModel.getArticlePagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<Article>>() {
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


    }

    private void showCategory() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] selectedItem = new String[1];

        // Set the alert dialog title
        builder.setTitle("Select Category.");


        // Initializing an array of language
        final String[] Categories = new String[]{
                "General",
                "Entertainment",
                "Business",
                "Health",
                "Science",
                "Sports",
                "Technology"
        };

        // Set a single choice items list for alert dialog
        builder.setSingleChoiceItems(
                Categories, // Items list
                -1, // Index of checked item (-1 = no selection)
                new DialogInterface.OnClickListener() // Item click listener
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Get the alert dialog selected item's text
                        selectedItem[0] = Arrays.asList(Categories).get(i);

                        // Display the selected item's text on snack bar
                        //Toast.makeText(getApplicationContext(), "Checked : " + selectedItem[0], Toast.LENGTH_SHORT).show();
                    }
                });

        // Set the a;ert dialog positive button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Just dismiss the alert dialog after selection
                // Or do something now
                continue_selectCategory(selectedItem[0]);


            }
        });

        // Create the alert dialog
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);

        // Finally, display the alert dialog
        dialog.show();
    }

    private void continue_selectCategory(String key) {

        if (key == "General") {
            Constants.CATEGORY="general";
            getData();
        }
        else if (key == "Entertainment") {
            Constants.CATEGORY="entertainment";
            getData();
        }
        else if (key == "Business") {

            Constants.CATEGORY="business";
            getData();
        }
        else if (key == "Health") {
            Constants.CATEGORY="health";
            getData();

        }else if (key == "Science") {
            Constants.CATEGORY="science";
            getData();

        }else if (key == "Sports") {
            Constants.CATEGORY="sports";
            getData();

        }else if (key == "Technology") {
            Constants.CATEGORY="technology";
            getData();

        }

        else {
            Constants.CATEGORY="general";
            getData();
            //do nothing
        }
    }


}