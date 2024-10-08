package com.aditya.anews;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    TextView userProf;
    Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userProf = findViewById(R.id.username);
        logOut = findViewById(R.id.logOut);

//        final SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
//        final String credentials = sharedPreferences.getString("mobile","");
//        final String name = sharedPreferences.getString("name","");

        SharedPreferences sh = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String mob = sh.getString("anmobile", "");
        String name = sh.getString("anname", "");

        if (name.isEmpty()) {
            name = "User";
            Intent intent = new Intent(Profile.this, LogIn.class);
            startActivity(intent);
            finish();
//            setUserName();
        } else {
            userProf.setText("Welcome " + name);
        }
        Toast.makeText(this, "Name: " + name, Toast.LENGTH_SHORT).show();

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(Profile.this, "Logged out", Toast.LENGTH_SHORT).show();
                finish();

//                AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
//                builder.setMessage("Are you sure....?");
//                builder.setTitle("Do you want to Logout");
//                builder.setCancelable(false);
//
//                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
//                    final SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.clear();
//                    editor.commit();
//                    Toast.makeText(Profile.this, "Logged out", Toast.LENGTH_SHORT).show();
//                });

            }
        });
    }

    private  void setUserName(){
        SharedPreferences sh = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String name = sh.getString("anname","");

        userProf.setText("Welcome "+name);
    }
}