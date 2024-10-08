package com.aditya.anews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rcView;
    List<Article> articleList = new ArrayList<>();
    NewsAdapter adapter;
    LinearProgressIndicator progressIndicator;
    Button gen, tech, entr, busi, health, sci, sports;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rcView = findViewById(R.id.recylerView);
        progressIndicator = findViewById(R.id.progressbar);

        gen = findViewById(R.id.general);
        tech = findViewById(R.id.technology);
        entr = findViewById(R.id.entertainment);
        busi = findViewById(R.id.business);
        health = findViewById(R.id.health);
        sci = findViewById(R.id.science);
        sports = findViewById(R.id.sports);
        gen.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_500));

        gen.setOnClickListener(this);
        tech.setOnClickListener(this);
        entr.setOnClickListener(this);
        busi.setOnClickListener(this);
        health.setOnClickListener(this);
        sci.setOnClickListener(this);
        sports.setOnClickListener(this);

        setUpRecyclerView();
        getNews("GENERAL");

    }

    void setUpRecyclerView(){
        rcView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter(articleList);
        rcView.setAdapter(adapter);
    }

    void chngProgress(Boolean flag){
        if(flag){
            progressIndicator.setVisibility(View.VISIBLE);
        }else{
            progressIndicator.setVisibility(View.INVISIBLE);
        }
    }

//    function for getting news
    public void getNews(String category){
        chngProgress(true);
        NewsApiClient newsApiClient = new NewsApiClient("15cd088670f04444a524e3a24fc94f3c");

        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .country("in")
                        .language("en")
                        .category(category)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {

                    @Override
                    public void onSuccess(ArticleResponse response) {

                        runOnUiThread(()->{
                            chngProgress(false);
                            articleList = response.getArticles();
                            adapter.updateDate(articleList);
                            adapter.notifyDataSetChanged();
                        });
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.i("Got Failure",throwable.getMessage());
                    }
                }
        );
    }

//    for adding the menu to actiobar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_menu, menu);
        return true;
    }


//this works for opening profile or login page when clicked on profile button in actionbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Profile.class);
                startActivity(intent);

                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        if(true){
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Home.this);

            // Set the message show for the Alert time
            builder.setMessage("Do you want to exit ?");

            // Set Alert Title
            builder.setTitle("Alert !");

            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
            builder.setCancelable(false);

            // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                // When the user click yes button then app will close
                finish();
            });

            // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                // If user click no then dialog box is canceled.
                dialog.cancel();
            });

            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();
        }else
            super.onBackPressed();
    }

    void changeColor(){
        gen.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_500));
        tech.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_500));
        entr.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_500));
        health.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_500));
        sci.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_500));
        busi.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_500));
        sports.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_500));
    }

    @Override
    public void onClick(View view) {
        Button clicked = (Button) view;
        String catText = clicked.getText().toString();
        getNews(catText);
        rcView.smoothScrollToPosition(0);
        changeColor();
        clicked.setBackgroundColor(Color.BLUE);
    }
}