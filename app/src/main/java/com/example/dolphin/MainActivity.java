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

        // If the Android version is lower than Jellybean, use this call to hide
        // the status bar.
//        if (Build.VERSION.SDK_INT < 16) {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }
        setContentView(R.layout.activity_main);

//        View decorView = getWindow().getDecorView();
        // Hide the status bar.
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
        // Hide Action bar
        //ActionBar actionBar = getActionBar();
        //actionBar.hide();
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