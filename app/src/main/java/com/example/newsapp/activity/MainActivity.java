package com.example.newsapp.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.models.Article;
import com.example.newsapp.repositories.ArticleRepository;
import com.example.newsapp.requests.ArticleApi;
import com.example.newsapp.requests.ServiceGenerator;
import com.example.newsapp.requests.responses.NewsApiResponse;
import com.example.newsapp.util.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Article> articleList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_arabic_news, R.id.navigation_world_news,
                R.id.navigation_country_news, R.id.navigation_source_news,
                R.id.navigation_about)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


//        ArticleRepository repository;
//        repository=ArticleRepository.getInstance();

        //getData(1);

        //List<Article> arr =new ArrayList<>();
       // arr.addAll(getData(2));
        //Toast.makeText(this, String.valueOf(arr.get(1).getTitle()), Toast.LENGTH_SHORT).show();


    }

    public List<Article> getData(Integer page)
    {
        final ArticleApi articleApi= ServiceGenerator.getArticleApi();
        Call<NewsApiResponse> responseCall =articleApi.getData(Constants.SEARCH_WORD,1,Constants.PAGE_SIZE,Constants.API_KEY);


        responseCall.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response)
            {

                NewsApiResponse newsApiResponse=response.body();

                if (response.code()==200)
                {
                    //Toast.makeText(MainActivity.this, String.valueOf(newsApiResponse.getArticles().get(0).getTitle()), Toast.LENGTH_SHORT).show();
                    articleList=newsApiResponse.getArticles();

                }
                else
                {
                    Toast.makeText(MainActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {

                t.getMessage().trim();
            }
        });
        return articleList;
    }


}

