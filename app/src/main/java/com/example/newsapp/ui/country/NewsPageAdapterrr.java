package com.example.newsapp.ui.country;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.activity.DetailsActivity;
import com.example.newsapp.models.Article;
import com.squareup.picasso.Picasso;

public class NewsPageAdapterrr extends PagedListAdapter<Article, NewsPageAdapterrr.ArticleViewHolder> {


    private Context context;
    public NewsPageAdapterrr(Context context) {
        super(DIFF_CALLBACK);
        this.context=context;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {

        final Article one_article =getItem(position);
        holder.news_title.setText(one_article.getTitle());
        holder.news_publishedAt.setText(one_article.getPublishedAt().substring(0,10));
        holder.news_author.setText(one_article.getAuthor());

        String path=one_article.getUrlToImage();
        if(path==null || path.trim().length()==0)
            path="www.google.com/ahmed.png";

        Picasso.with(context).load(path).error(R.drawable.ic_home_black_24dp).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailsActivity.class);

                intent.putExtra("url",one_article.getUrl());

                context.startActivity(intent);
            }
        });

    }

    private  static DiffUtil.ItemCallback<Article>  DIFF_CALLBACK=
            new DiffUtil.ItemCallback<Article>() {
                @Override
                public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
                    return oldItem.getUrl().equals(newItem.getUrl());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
                    return oldItem.equals(newItem);
                }
            };

    class ArticleViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView news_title;
        private TextView news_author;
        private TextView news_publishedAt;


        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.news_imageView);
            news_title = (TextView) itemView.findViewById(R.id.news_title);
            news_author = (TextView) itemView.findViewById(R.id.news_author);
            news_publishedAt = (TextView) itemView.findViewById(R.id.news_publishedAt);
        }
    }
}
