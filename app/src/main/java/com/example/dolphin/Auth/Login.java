package com.example.dolphin.Auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dolphin.LandingActivity;
import com.example.dolphin.R;
import com.example.dolphin.constants.Constants;
import com.example.dolphin.session.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends Activity {


    SessionManager sessionManager = new SessionManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button signIn_button, redirect_to_register;
        final EditText username ,password;

        signIn_button = findViewById(R.id.signIn);
        username = findViewById(R.id.l_username);
        password = findViewById(R.id.l_password);
        redirect_to_register = findViewById(R.id.b_redirectToSignUp);

        sessionManager.setSharedPreference(getApplicationContext());

//        HashMap<String, String> user =  sessionManager.getUserDetail();
//        if(!user.isEmpty()){
//            Intent intent = new Intent(Login.this, LandingActivity.class);
//            startActivity(intent);
//        }


        signIn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String l_username = username.getText().toString().trim();
                String l_password = password.getText().toString().trim();

                if (l_username.length() == 0) {
                    Toast.makeText(getApplicationContext(), "please enter username..", Toast.LENGTH_SHORT).show();
                } else if (l_password.length() == 0) {
                    Toast.makeText(Login.this, "please enter password..", Toast.LENGTH_SHORT).show();
                } else {
                    signService(l_username, l_password);
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

    void signService(final String username, String password){

        Constants constants = new Constants();
        String loginUrl = constants.getSigning_url();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JSONObject signInData = new JSONObject();
        final boolean[] success = {false};

        try {
            signInData.put("username", username);
            signInData.put("password", password);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    loginUrl,
                    signInData,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);
                            try {
                                if (response.getString("value").equals("pass")) {
                                    sessionManager.createLoginSession(response.getString("name"), username);
                                    Intent intent = new Intent(Login.this, LandingActivity.class);
                                    startActivity(intent);
                                } else if (response.getString("value").equals("username_not_found")) {
                                    Toast.makeText(getApplicationContext(), "Username not found, Please Register.", Toast.LENGTH_LONG).show();
                                } else if (response.getString("value").equals("incorrect_password")) {
                                    Toast.makeText(getApplicationContext(), "Incorrect password try again.", Toast.LENGTH_LONG).show();
                                } else if (response.getString("value").equals("details_not_found")) {
                                    Toast.makeText(getApplicationContext(), "please enter username & password.", Toast.LENGTH_LONG).show();
                                } else if (response.getString("value").equals("connection_error")) {
                                    Toast.makeText(getApplicationContext(), "server error please try again later", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "unrecognised error", Toast.LENGTH_LONG).show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "server down. Try again later..", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(getApplicationContext(), "server down. Try again later..", Toast.LENGTH_SHORT).show();
                }

            });

            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}