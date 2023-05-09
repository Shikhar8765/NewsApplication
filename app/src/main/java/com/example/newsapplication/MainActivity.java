package com.example.newsapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategoriesClickInterface {
    //b20c9668f58445949987c1c37dca847c\
    private RecyclerView newsRV, categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoriesRVModel> categoriesRVModelArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV = findViewById(R.id.idRVNews);
        categoryRV = findViewById(R.id.idRVCategories);
        loadingPB = findViewById(R.id.idPBLoading);
        articlesArrayList=new ArrayList<>();
        categoriesRVModelArrayList =new ArrayList<>();
        newsRVAdapter =new NewsRVAdapter(articlesArrayList, this);
    categoryRVAdapter=new CategoryRVAdapter(categoriesRVModelArrayList,this,this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);
        getNews("All");
        getCategories();
        newsRVAdapter.notifyDataSetChanged();
    }

    private  void getCategories()
    {

        categoriesRVModelArrayList.add(new CategoriesRVModel("All", "https://images.unsplash.com/photo-1495020689067-958852a7765e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8bmV3c3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=700&q=60"));
        categoriesRVModelArrayList.add(new CategoriesRVModel("Technology", "https://images.unsplash.com/photo-1518770660439-4636190af475?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8VGVjaG5vbG9neXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=700&q=60"));
        categoriesRVModelArrayList.add(new CategoriesRVModel("Science", "https://images.unsplash.com/photo-1532094349884-543bc11b234d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8U2NpZW5jZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=700&q=60"));
        categoriesRVModelArrayList.add(new CategoriesRVModel("Sports", "https://images.unsplash.com/photo-1461896836934-ffe607ba8211?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8c3BvcnRzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=700&q=60"));
        categoriesRVModelArrayList.add(new CategoriesRVModel("General", "https://images.unsplash.com/photo-1494059980473-813e73ee784b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8Z2VuZXJhbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=700&q=60"));
        categoriesRVModelArrayList.add(new CategoriesRVModel("Business", "https://images.unsplash.com/photo-1665686308827-eb62e4f6604d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8QnVzaW5lc3N8ZW58MHx8MHx8&auto=format&fit=crop&w=700&q=60"));
        categoriesRVModelArrayList.add(new CategoriesRVModel("Entertainment", "https://images.unsplash.com/photo-1603190287605-e6ade32fa852?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=700&q=60"));
        categoriesRVModelArrayList.add(new CategoriesRVModel("Health", "https://images.unsplash.com/photo-1505576399279-565b52d4ac71?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8aGVhbHRofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=700&q=60"));
        categoryRVAdapter.notifyDataSetChanged();
    }
    private void getNews(String category)
    {
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String catergoryURL="https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apikey=b20c9668f58445949987c1c37dca847c";
        String url="https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apikey=b20c9668f58445949987c1c37dca847c";
        String BASE_Url="https://newsapi.org/";
        Retrofit retrofit= new Retrofit.Builder().baseUrl(BASE_Url).addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitAPI retrofitAPI=retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if(category.equals("All"))
        {
           call=retrofitAPI.getAllNews(url);
        }
        else
        {
            call=retrofitAPI.getNewsByCategory(catergoryURL);
        }
        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal=response.body();
                loadingPB.setVisibility(View.GONE);
              ArrayList<Articles> articles=newsModal.getArticles();
              for(int i=0;i<articles.size();i++)
              {
                  articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));

              }
                 newsRVAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to Get News", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
        String category=categoriesRVModelArrayList.get(position).getCategory();
        getNews(category);

    }
}