package com.example.dolphin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dolphin.Auth.Login;
import com.example.dolphin.session.SessionManager;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SessionManager sessionManager = new SessionManager();
        sessionManager.setSharedPreference(getApplicationContext());

        HashMap<String, String> user =  sessionManager.getUserDetail();
        if(!user.isEmpty()){
            Intent intent = new Intent(MainActivity.this, LandingActivity.class);
            startActivity(intent);
        }

        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }
}