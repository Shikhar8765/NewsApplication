package com.example.newsapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {
String title,desc,content,imageUrl,url;
private TextView titleTV,subDescTV,contentTv;
private ImageView newsIV;
private Button readnewsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title=getIntent().getStringExtra("title");
       desc=getIntent().getStringExtra("desc");
        content=getIntent().getStringExtra("content");
        imageUrl=getIntent().getStringExtra("image");
      url=getIntent().getStringExtra("url");
      titleTV=findViewById(R.id.idTVTitle);
        subDescTV=findViewById(R.id.idTVSubDesc);
        contentTv=findViewById(R.id.idTVContent);
        newsIV=findViewById(R.id.idIVNews);
        readnewsBtn=findViewById(R.id.idBTnReadNews);
        titleTV.setText(title);
        subDescTV.setText(desc);
        contentTv.setText(content);
        Picasso.get().load(imageUrl).into(newsIV);
        readnewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


    }
}