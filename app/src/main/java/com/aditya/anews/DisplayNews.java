package com.aditya.anews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DisplayNews extends AppCompatActivity {

    WebView wbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_news);

        wbView = findViewById(R.id.webView);
        String url = getIntent().getStringExtra("url");
        WebSettings webSettings = wbView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wbView.setWebViewClient(new WebViewClient());
        wbView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {

        if(wbView.canGoBack()){
            wbView.goBack();
        }
        else
            super.onBackPressed();
    }
}