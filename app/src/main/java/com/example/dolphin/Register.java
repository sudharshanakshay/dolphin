package com.example.dolphin;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class Register extends AppCompatActivity {

    Button signUp_button;
    EditText username, password, name, email;

    String regularExpression = "^([A-Z]+)([a-z0-9]*)([@$!][A-Za-z0-9])";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.r_name);
        username = (EditText) findViewById(R.id.r_username);
        email = (EditText) findViewById(R.id.r_email);
        password = (EditText) findViewById(R.id.r_password);


        signUp_button = (Button) findViewById(R.id.signIn);

        signUp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r_name = name.getText().toString();
                String r_username = username.getText().toString();
                String r_email = email.getText().toString();
                String r_password = password.getText().toString();

                if (!validatePassword(r_password)) {
                    Toast.makeText(Register.this, "password has some constraint!", Toast.LENGTH_SHORT).show();
                } else {


                }
            }
        });

    }

    public boolean validatePassword(String pass) {
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(pass);

        return matcher.matches();
    }
}
