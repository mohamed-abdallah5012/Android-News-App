package com.example.newsapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.activity.DetailsActivity;
import com.example.newsapp.R;
import com.example.newsapp.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Article> articleList ;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView imageView;
        private TextView news_title;
        private TextView news_author;
        private TextView news_publishedAt;


        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.news_imageView);
            news_title = (TextView) view.findViewById(R.id.news_title);
            news_author = (TextView) view.findViewById(R.id.news_author);
            news_publishedAt = (TextView) view.findViewById(R.id.news_publishedAt);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NewsAdapter(Context mContext, List<Article> articleList) {
        this.mContext = mContext;
        this.articleList = articleList;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);

        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Article one_article = articleList.get(position);
        holder.news_title.setText(one_article.getTitle());
        holder.news_publishedAt.setText(one_article.getPublishedAt().substring(0,10));
        holder.news_author.setText(one_article.getAuthor());

        String path=one_article.getUrlToImage();
        if(path==null || path.trim().length()==0)
        path="www.google.com/ahmed.pnsubmitListg";

        Picasso.with(mContext).load(path).error(R.drawable.ic_home_black_24dp).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(mContext, DetailsActivity.class);

            intent.putExtra("url",one_article.getUrl());

            mContext.startActivity(intent);
        }
    });
}

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}

