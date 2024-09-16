package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<CategoryModel> categoryModels;
    private Context context;
    private CategoryClickInterface categoryClickInterface;

    public CategoryAdapter(ArrayList<CategoryModel> categoryModels, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryModels = categoryModels;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    public CategoryAdapter(ArrayList<CategoryModel> categoryModels, Context context) {
        this.categoryModels = categoryModels;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories, parent, false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoryModel categoryModel = categoryModels.get(position);
        holder.categoryTitle.setText(categoryModel.getCategory());
        Picasso.get().load(categoryModel.getCategoryImageUrl()).into(holder.categoryImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public interface CategoryClickInterface {
        void onCategoryClick(int position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryTitle;
        private ImageView categoryImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.category_title);
            categoryImage = itemView.findViewById(R.id.image);
        }
    }
}
