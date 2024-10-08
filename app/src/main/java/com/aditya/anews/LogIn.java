package com.aditya.anews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {

    TextInputEditText mobileEdit, passwordEdit;
    Button login;
    String mobileString,passString;

    DatabaseReference userInfoReference;
    String getMobFromDatabase;
    String getNameFromDatabase;
    String getPassFromDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

//        SharedPreferences sh = getSharedPreferences("Data", Context.MODE_PRIVATE);
//        String mob = sh.getString("anmobile","");
//        String name = sh.getString("anname","");
//
//        if(!name.isEmpty()){
//            Toast.makeText(this, "Logged in already", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(LogIn.this, Home.class);
//            startActivity(intent);
//            finish();
//        }else{
////            userProf.setText("Welcome "+name);
//        }

        TextView signUpMov = findViewById(R.id.signUpMov);

        mobileEdit = findViewById(R.id.mobile);
        passwordEdit = findViewById(R.id.passwordlogin);
        login = findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobileString = mobileEdit.getText().toString().trim();
                passString = passwordEdit.getText().toString().trim();

                //check if mobile and pasword is empty or not.
                if(mobileString.isEmpty()){
                    Toast.makeText(LogIn.this, "mobile number should not be empty.", Toast.LENGTH_SHORT).show();
                    mobileEdit.requestFocus();
                    return;
                }else{
                    if(passString.isEmpty()){
                        Toast.makeText(LogIn.this, "mobile number should not be empty.", Toast.LENGTH_SHORT).show();
                        passwordEdit.requestFocus();
                        return;
                    }
                }

//                if mobile and password is not empty then check login credentails from firebase.
                FirebaseDatabase.getInstance().getReference().child("LoginInformation").child("User")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds: snapshot.getChildren()){
                                    String dbmobile = ds.child("mail").getValue().toString().trim();

                                    if(mobileString.equals(dbmobile)){
                                        if(passString.equals(ds.child("password").getValue().toString().trim())){
                                            Toast.makeText(LogIn.this, "Logged in successfully", Toast.LENGTH_SHORT).show();

                                            SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);

                                            String name= ds.child("name").getValue().toString();
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("anname", name);
                                            editor.putString("anmobile", mobileString);
                                            editor.putString("anpassword", passString);
                                            editor.commit();

                                            Toast.makeText(LogIn.this, "Welcome "+name+"", Toast.LENGTH_SHORT).show();

//                                            Intent intent = new Intent(LogIn.this, .class);
//                                            startActivity(intent);
                                            finish();
                                        }else{
                                            Toast.makeText(LogIn.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                                            passwordEdit.requestFocus();
                                            return;
                                        }
                                    }else{
                                        Toast.makeText(LogIn.this, "User not found", Toast.LENGTH_SHORT).show();
                                        mobileEdit.requestFocus();
                                        return;
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
        });


        signUpMov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}