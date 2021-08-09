package com.example.dolphin;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dolphin.Auth.Authenticate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    Button signUp_button, redirect_to_login;
    EditText username, password, name, email;

    String regularExpression = "^([A-Z]+)([a-z0-9]*)([@$!][A-Za-z0-9])";

    Authenticate authenticate = new Authenticate();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.r_name);
        username = (EditText) findViewById(R.id.r_username);
        email = (EditText) findViewById(R.id.r_email);
        password = (EditText) findViewById(R.id.r_password);


        signUp_button = (Button) findViewById(R.id.signIn);
        redirect_to_login = (Button)findViewById(R.id.b_redirectToLogin);

        final Authenticate.SignUp[] signUpClass = new Authenticate.SignUp[1];

        signUp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r_name = name.getText().toString();
                String r_username = username.getText().toString();
                String r_email = email.getText().toString();
                String r_password = password.getText().toString();


                ProgressBar progressBar;

                if (r_password.length()<0) {
                    Toast.makeText(Register.this, "password has some constraint!", Toast.LENGTH_SHORT).show();
                } else {
                    signUpClass[0] = authenticate.new SignUp(r_username, r_name,r_email, r_password, Register.this);
                    if(!signUpClass[0].isAlive()){
                        signUpClass[0].start();
                        progressBar = new ProgressBar(Register.this, null);
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        redirect_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    public boolean validatePassword(String pass) {
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(pass);

        return matcher.matches();
    }
}
