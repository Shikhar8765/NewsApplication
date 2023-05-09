package com.example.newsapplication;

import static com.example.newsapplication.R.id.idIVCategory;
import static com.example.newsapplication.R.id.idTVCategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {
    private ArrayList<CategoriesRVModel> categoriesRVModels;
    private Context context;
    private CategoriesClickInterface categoriesClickInterface;

    public CategoryRVAdapter(ArrayList<CategoriesRVModel> categoriesRVModels, Context context, CategoriesClickInterface categoriesClickInterface) {
        this.categoriesRVModels = categoriesRVModels;
        this.context = context;
        this.categoriesClickInterface = categoriesClickInterface;
    }

    @NonNull
    @Override
    public CategoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_item, parent, false);
        return new CategoryRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdapter.ViewHolder holder, int position) {
       CategoriesRVModel categoriesRVModel=categoriesRVModels.get(position);
       holder.categoryTV.setText(categoriesRVModel.getCategory());
        Picasso.get().load(categoriesRVModel.getCategoryImageUrl()).into(holder.categoryIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriesClickInterface.onCategoryClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoriesRVModels.size();
    }

    public interface CategoriesClickInterface {
        void onCategoryClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryTV;
        private ImageView categoryIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIV = itemView.findViewById(idIVCategory);
            categoryTV = itemView.findViewById(idTVCategory);
        }
    }
}
