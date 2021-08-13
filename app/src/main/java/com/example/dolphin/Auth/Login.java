package com.example.dolphin.Auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dolphin.LandingActivity;
import com.example.dolphin.R;

public class Login extends AppCompatActivity {

    Button signIn_button, redirect_to_register;
    EditText username,password ;
    Context _context;
    Authenticate authenticate = new Authenticate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn_button = (Button) findViewById(R.id.signIn);
        username = (EditText) findViewById(R.id.l_username);
        password = (EditText) findViewById(R.id.l_password);
        redirect_to_register = (Button) findViewById(R.id.b_redirectToSignUp);


        signIn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String l_username = username.getText().toString();
                String l_password = password.getText().toString();

//                if (l_password.length()<0) {
//                    Toast.makeText(Login.this, "please enter password..", Toast.LENGTH_SHORT).show();
//                } else {
//                    Authenticate.Login login = authenticate.new Login(l_username,l_password, Login.this);
//                    if(!login.isAlive()){
//                        login.start();
//                    }
//                }

                if(true){
                    Intent intent = new Intent(Login.this, LandingActivity.class);
                    startActivity(intent);
                }
            }


        });

        redirect_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
