package com.example.dolphin.Auth;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dolphin.LandingActivity;
import com.example.dolphin.constants.Constants;
import com.example.dolphin.session.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

public class Authenticate extends Application {

    SessionManager sessionManager = new SessionManager();

    public class SignUp{
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



    }


}

//                if (response == null) {
//                        Toast.makeText(context, "couldn't login, try again", Toast.LENGTH_LONG).show();
//                        return;
//                        }
