package com.example.newsapp;

import static android.app.ProgressDialog.show;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.CategoryClickInterface {

    private RecyclerView newsRv,categoryRv;
    private ProgressBar loader;
    private ArrayList<CategoryModel> categoryModels;
    private ArrayList<Article> articles;
    private CategoryAdapter categoryAdapter;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRv = findViewById(R.id.newsList);
        categoryRv = findViewById(R.id.category);
        loader = findViewById(R.id.loader);
        articles = new ArrayList<>();
        categoryModels = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryModels,this,this::onCategoryClick);
        newsAdapter = new NewsAdapter(articles,this);
        newsRv.setLayoutManager(new LinearLayoutManager(this));
        newsRv.setAdapter(newsAdapter);
        categoryRv.setAdapter(categoryAdapter);
        getCategories();
        getNews("All");
        newsAdapter.notifyDataSetChanged();
    }
    private void getCategories(){
        categoryModels.add(new CategoryModel("All","https://plus.unsplash.com/premium_photo-1688561384438-bfa9273e2c00?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        categoryModels.add(new CategoryModel("Tech","https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        categoryModels.add(new CategoryModel("Science","https://plus.unsplash.com/premium_photo-1676325101955-1089267548d4?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        categoryModels.add(new CategoryModel("sports","https://plus.unsplash.com/premium_photo-1664537975122-9c598d85816e?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        categoryModels.add(new CategoryModel("General","https://images.unsplash.com/photo-1455849318743-b2233052fcff?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        categoryModels.add(new CategoryModel("Business","https://images.unsplash.com/photo-1696685733938-78ab563effe1?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        categoryModels.add(new CategoryModel("Entertainment","https://plus.unsplash.com/premium_photo-1683740128615-419625f14311?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        categoryModels.add(new CategoryModel("Health","https://plus.unsplash.com/premium_photo-1667762241847-37471e8c8bc0?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
    }

    private void getNews(String category){
        loader.setVisibility(View.VISIBLE);
        articles.clear();
        String categoryURL = "https://newsapi.org/v2/top-headlines/sources?category="+category+"&apiKey=API_KEY";
        String url = "https://newsapi.org/v2/top-headlines/sources?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=API_KEY";
        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModel> call;
        if(category.equals("All")){
            call = retrofitAPI.getNews(url);
        }else {
            call = retrofitAPI.getNewsByCategory(categoryURL);
        }
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();
                loader.setVisibility(View.GONE);
                ArrayList<Article> respArticles = newsModel.getArticles();
                for(int i=0;i<respArticles.size();i++){
                    articles.add(new Article(respArticles.get(i).getTitle(),respArticles.get(i).getDescription(),respArticles.get(i).getUrl(),respArticles.get(i).getUrlToImage(),respArticles.get(i).getContent()));
                }
                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
        String category = categoryModels.get(position).getCategory();
        getNews(category);
    }
}