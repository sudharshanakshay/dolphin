package com.example.dolphin.Auth;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Authenticate {

    public class SignUp extends Thread  {
        private final String username;
        private final String name;
        private final String email;
        private final String password;

        Context context;

        public SignUp(String username, String name, String email, String password, Context context){

            this.username = username;
            this.name = name;
            this.email = email;
            this.password = password;
            this.context = context;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void run() {
            try {
                String postUrl = "http://a70c8ac2e069.ngrok.io/signup.php";

                RequestQueue requestQueue = Volley.newRequestQueue(context);

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
                            }
                        },
                    new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                requestQueue.add(jsonObjectRequest);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public class Login extends Thread  {
        protected String username;
        protected String password;
        protected Context context;

        public Login(String username, String password, Context context) {
            this.username = username;
            this.password = password;
            this.context = context;
        }

        @Override
        public void run() {
            String loginUrl = "http://a70c8ac2e069.ngrok.io/signin.php";

            RequestQueue requestQueue = Volley.newRequestQueue(context);

            JSONObject signInData = new JSONObject();

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
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                requestQueue.add(jsonObjectRequest);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}