package com.example.newsapp.ui.country;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.example.newsapp.models.Article;
import com.example.newsapp.util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CountryFragment extends Fragment {

    private CountryViewModel countryViewModel;
    private NewsPageAdapterrr adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    //private SearchView searchView;

    private  PagedList<Article> articles_list;
    private static final String TAG ="CountryFragment" ;







    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        countryViewModel =
                ViewModelProviders.of(this).get(CountryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_host, container, false);




        //searchView=root.findViewById(R.id.search_view);
        //search(searchView);



        progressBar = root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = root.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        adapter=new NewsPageAdapterrr(getContext());
        ConnectivityManager manager = (ConnectivityManager) requireActivity().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo network = manager.getActiveNetworkInfo();
        if (network != null && network.isConnected()) {
            showCategory();

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
    private void getData()
    {
        countryViewModel.getArticlePagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<Article>>() {
            @Override
            public void onChanged(PagedList<Article> articles) {

                adapter.submitList(articles);
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void showCategory() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] selectedItem = new String[1];

        // Set the alert dialog title
        builder.setTitle("Select Country.");


// Initializing an array of language
        final String[] Categories = new String[]{
                "Egypt",
                "United States of America",
                "United Kingdom",
                "United Arab Emirates",
                "Turkey"
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

        if (key == "Egypt") {
            Constants.COUNTRY="eg";
            getData();
        }
        else if (key == "United States of America") {
            Constants.COUNTRY="us";
            getData();
        }
        else if (key == "United Kingdom") {

            Constants.COUNTRY="gb";
            getData();

        }
        else if (key == "United Arab Emirates") {
            Constants.COUNTRY="ae";
            getData();

        }else if (key == "Turkey") {
            Constants.COUNTRY="tr";
            getData();

        }

        else {
            Constants.COUNTRY="eg";
            getData();
            //do nothing
        }
    }


//    private void search(SearchView searchView) {
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                co_search(s);
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String s) {
//                co_search(s);
//                return false;
//            }
//        });

 //   }
   /* private void co_search(String word)
    {
        Constants.SEARCH_WORD=word;

        countryViewModel.getArticlePagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<Article>>() {
            @Override
            public void onChanged(PagedList<Article> articles) {
                adapter.submitList(articles);
                progressBar.setVisibility(View.GONE);
            }
        });
        //recyclerView.setAdapter(adapter);

    }*/
}