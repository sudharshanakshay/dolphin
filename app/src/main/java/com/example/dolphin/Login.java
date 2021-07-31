package com.example.dolphin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    Button signIn_button;
    TextView signIn_username, signIn_password;

//    String regularExpression = "Expression\"^(?=.*[A-Z])(?=.*[a-z])(?=.*\\\\d)(?=.*[@$!][A-Zaz\\\\n" +
//            "d@$!]{8,}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        signIn_button = (Button) findViewById(R.id.singIn);
        signIn_username = (TextView) findViewById(R.id.signIn_username);
        signIn_password = (TextView) findViewById(R.id.signIn_password);

        Bundle extras = getIntent().getExtras();

        final String username_from_signUp;
        final String password_from_signUp;
        if(extras!=null){
            username_from_signUp  = extras.getString("username");
            password_from_signUp  = extras.getString("password");
        }else{
            username_from_signUp = "";
            password_from_signUp = "";
        }


        signIn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = signIn_username.getText().toString();
                String password = signIn_password.getText().toString();

//                if (validatePassword(password)) {
//
//
//
//                } else {
//                    Toast.makeText(SignIn.this, "password has some constraint!", Toast.LENGTH_SHORT).show();
//                }
                if (username.equals(username_from_signUp) && password.equals(password_from_signUp)) {

                    Intent goto_landingPage = new Intent(SignIn.this, LandingPage.class);
                    startActivity(goto_landingPage);

                }else{
                    Toast.makeText(SignIn.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    public boolean validatePassword (String pwd){
//        Pattern pattern = Pattern.compile(regularExpression);
//        Matcher matcher = pattern.matcher(pwd);
//        return matcher.matches();
//    }

