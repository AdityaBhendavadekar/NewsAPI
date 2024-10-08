package com.aditya.anews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisNewsSimple extends AppCompatActivity {
    TextView textView, newstitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_news_simple);


//        textView = findViewById(R.id.);
        textView = findViewById(R.id.content);
        newstitle = findViewById(R.id.newTitle);

        String content = getIntent().getStringExtra("content");
        String title = getIntent().getStringExtra("newstitle");

        newstitle.setText(title);
        textView.setText(content);
    }
}