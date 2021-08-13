package com.example.dolphin.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.dolphin.Auth.Login;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences shrdPref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    //pref file name
    private  String PREF_NAME = "dolphinPref";
    private  String IS_LOGIN = "is_login";
    private  String KEY_USERNAME = "username";
    public   String KEY_NAME = "name";
    public   String KEY_EMAIL = "email";

    public SessionManager(Context context){
        this._context = context;
        shrdPref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = shrdPref.edit();
    }

    public void createLoginSession(String name, String username){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_USERNAME, username);
        editor.commit();
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, shrdPref.getString(KEY_NAME, null));
        user.put(KEY_USERNAME, shrdPref.getString(KEY_USERNAME, null));
        user.put(KEY_EMAIL, shrdPref.getString(KEY_EMAIL, null));
        return  user;
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent intent = new Intent(_context, Login.class);
            // flag to close all the activity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // new flag to start new activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // stating loign activity
            _context.startActivity(intent);
        }
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(_context, Login.class);
        // flag to close all the activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // new flag to start new activity
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // stating loign activity
        _context.startActivity(intent);
    }

    public boolean isLoggedIn(){
        return shrdPref.getBoolean(IS_LOGIN, false);
    }

}
