package com.example.newsapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRVAdapter  extends RecyclerView.Adapter<NewsRVAdapter.ViewHolder> {
    private ArrayList<Articles> articlesArrayList;
    Context context;
    public NewsRVAdapter(@NonNull ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }
    @NonNull
    @Override

    public NewsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRVAdapter.ViewHolder holder, int position) {
        Articles articles=articlesArrayList.get(position);
        holder.subTitleTV.setText(articles.getDescription());
        holder.titleTV.setText(articles.getTitle());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsTV);
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i=new Intent(context,NewsDetailActivity.class);
              i.putExtra("title", articles.getTitle());
              i.putExtra("content", articles.getContent());
              i.putExtra("desc", articles.getDescription());
              i.putExtra("url", articles.getUrl());
              i.putExtra("image", articles.getUrlToImage());
              context.startActivity(i);



          }
      });
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTV,subTitleTV;
        private ImageView newsTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV=itemView.findViewById(R.id.idTVNewsHeading);
            subTitleTV=itemView.findViewById(R.id.idTVSubtitle);
            newsTV=itemView.findViewById(R.id.idIVNews);
        }
    }
}
