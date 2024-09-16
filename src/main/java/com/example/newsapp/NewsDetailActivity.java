package com.example.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {
    TextView titleTv,descriptionTv,contentTv;
    ImageView imageUrlTv;
    String title,description,url,content,imageUrl;
    Button readNewsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        url = getIntent().getStringExtra("url");
        content = getIntent().getStringExtra("content");
        imageUrl = getIntent().getStringExtra("imageUrl");
        titleTv = findViewById(R.id.ivTitle);
        descriptionTv = findViewById(R.id.ivSubTitle);
        contentTv = findViewById(R.id.ivContent);
        imageUrlTv = findViewById(R.id.ivImage);
        readNewsBtn = findViewById(R.id.btnNews);

        titleTv.setText(title);
        descriptionTv.setText(description);
        contentTv.setText(content);
        Picasso.get().load(imageUrl).into(imageUrlTv);

        readNewsBtn.setOnClickListener(View -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
    }
}