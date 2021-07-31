package com.example.dolphin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button signIn_button;
    EditText username,password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn_button = (Button) findViewById(R.id.signIn);
        username = (EditText) findViewById(R.id.l_username);
        password = (EditText) findViewById(R.id.l_password);


        signIn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String l_username = username.getText().toString();
                String l_password = password.getText().toString();
            }


        });
    }
}
