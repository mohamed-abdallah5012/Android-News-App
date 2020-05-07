package com.example.newsapp.requests.responses;

import com.example.newsapp.models.Article;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewsApiResponse {


        @SerializedName("status")
        @Expose
        private String status;

        @SerializedName("totalResults")
        @Expose
        private int totalResults;


        @SerializedName("articles")
        @Expose
        private List<Article> articles  ;

    public List<Article> getArticles() {
        if (articles==null)
            articles=new ArrayList<>();
            return articles;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    @Override
    public String toString() {
        return "ArabicResponse{" +
                "status='" + status + '\'' +
                ", totalResults=" + totalResults +
                ", articles=" + articles +
                '}';
    }
}


