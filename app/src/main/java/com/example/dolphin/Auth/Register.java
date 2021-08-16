package com.example.dolphin.Auth;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    String regularExpression = "^([A-Z]+)([a-z0-9]*)([@$!][A-Za-z0-9])";

    SessionManager sessionManager = new SessionManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button signUp_button, redirect_to_login;
        final EditText username, password, name, email;

        name =  findViewById(R.id.r_name);
        username =  findViewById(R.id.r_username);
        email =  findViewById(R.id.r_email);
        password =  findViewById(R.id.r_password);
        signUp_button =  findViewById(R.id.signIn);
        redirect_to_login = findViewById(R.id.b_redirectToLogin);

        sessionManager.setSharedPreference(getApplicationContext());

        final Authenticate.SignUp[] signUpClass = new Authenticate.SignUp[1];

        signUp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r_name = name.getText().toString();
                String r_username = username.getText().toString();
                String r_email = email.getText().toString();
                String r_password = password.getText().toString();

                if (r_password.length()==0) {
                    Toast.makeText(Register.this, "please enter password..", Toast.LENGTH_SHORT).show();
                } else {
                    sinupService(r_name, r_username, r_email, r_password);
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

    public void sinupService(String name, final String username, String email, String password){
        try {
            Constants constants = new Constants();
            String postUrl = constants.getSignup_url();

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("username", username);
            jsonObject.put("name", name);
            jsonObject.put("email", email);
            jsonObject.put("password", password);

            System.out.println(jsonObject.toString());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    postUrl,
                    jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);
                            try {
                                if(response.getString("value").equals("pass")){
                                    sessionManager.createLoginSession(response.getString("name"),username);
                                    Intent intent = new Intent(Register.this, LandingActivity.class);
                                    startActivity(intent);
                                }else if(response.getString("value").equals("user_found_incorrect_password")){
                                    Toast.makeText(getApplicationContext(), "User found, incorrect password", Toast.LENGTH_LONG).show();
                                }else if(response.getString("value").equals("Registration_failed")){
                                    Toast.makeText(getApplicationContext(), "Registration failed, try again later.", Toast.LENGTH_LONG).show();
                                }else if (response.getString("value").equals("details_not_found")){
                                    Toast.makeText(getApplicationContext(), "please enter username & password.", Toast.LENGTH_LONG).show();
                                }else if(response.getString("value").equals("connection_error")){
                                    Toast.makeText(getApplicationContext(), "server error please try again later", Toast.LENGTH_LONG).show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "server down. Try again later..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
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

    public boolean validatePassword(String pass) {
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(pass);

        return matcher.matches();
    }
}
