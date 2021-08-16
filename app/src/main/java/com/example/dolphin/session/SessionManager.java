package com.example.dolphin.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.dolphin.Auth.Login;
import com.example.dolphin.MainActivity;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    //pref file name
    private  String PREF_NAME = "dolphinPref";
    private  String IS_LOGIN = "is_login";
    private  String KEY_USERNAME = "username";
    public   String KEY_NAME = "name";
    public   String KEY_EMAIL = "email";

    public void setSharedPreference(Context context){
        this.context = context;
        sharedPreferences =  context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void createLoginSession(String name, String username){

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.commit();
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, sharedPreferences.getString(KEY_NAME, null));
        user.put(KEY_USERNAME, sharedPreferences.getString(KEY_USERNAME, null));
        user.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));
        return  user;
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent intent = new Intent(context, Login.class);
            // flag to close all the activity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // new flag to start new activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // stating loign activity
            context.startActivity(intent);
        }
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, MainActivity.class);
        // flag to close all the activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // new flag to start new activity
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // stating loign activity
        context.startActivity(intent);
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

}
