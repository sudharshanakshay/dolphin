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

import static java.util.regex.Pattern.compile;

public class Register extends AppCompatActivity {

    Button signUp_button;
    TextView signUp_username, signUp_password;

    String regularExpression = "^([A-Z]+)([a-z0-9]*)([@$!][A-Za-z0-9])";
//    String regularExpression = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!][A-Za-z\\d@$!]{8,})$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUp_username = (TextView) findViewById(R.id.signUp_username);
        signUp_password = (TextView) findViewById(R.id.signUp_password);
        signUp_button = (Button) findViewById(R.id.signUp);

        signUp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = signUp_username.getText().toString();
                String password = signUp_password.getText().toString();

                if (!validatePassword(password)) {
                    Toast.makeText(SignUp.this, "password has some constraint!", Toast.LENGTH_SHORT).show();
                } else {

                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    bundle.putString("password", password);

                    Intent goto_landingPage = new Intent(Register.this, SignIn.class);

                    goto_landingPage.putExtras(bundle);
                    startActivity(goto_landingPage);

                }
//                Intent goto_landingPage = new Intent(SignUp.this, SignIn.class);
//                startActivity(goto_landingPage);

            }
        });

    }
    public boolean validatePassword( String pwd){
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(pwd);

        return matcher.matches();
    }
