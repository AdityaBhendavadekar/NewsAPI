package com.aditya.anews;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends  RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<Article> articleList;
    NewsAdapter(List<Article> articleList){
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_view, parent, false);
        return new NewsViewHolder(view);
    }

//    bind data with ui
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.titleText.setText(article.getTitle());
        holder.sourceText.setText(article.getSource().getName());
        Picasso.get().load(article.getUrlToImage())
                .error(R.drawable.no_image_icon)
                .placeholder(R.drawable.no_image_icon)
                .into(holder.imageView);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(view.getContext(), DisplayNews.class);
//                intent.putExtra("url", article.getUrl());
//                view.getContext().startActivity(intent);
//
////                Intent intent1 = new Intent(view.getContext(), DisNewsSimple.class);
////                intent1.putExtra("content",article.getContent());
////                intent1.putExtra("newstitle",article.getTitle());
////                view.getContext().startActivity(intent1);
//            }
//        });
    }

    void updateDate(List<Article> articles){
        articleList.clear();
        articleList.addAll(articles);
    }

    @Override
    public int getItemCount() {
       return articleList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView titleText, sourceText;
        ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.article_title);
            sourceText = itemView.findViewById(R.id.article_source);
            imageView = itemView.findViewById(R.id.article_img);
        }
    }
}
