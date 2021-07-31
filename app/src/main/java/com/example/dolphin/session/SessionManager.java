package com.example.dolphin.session;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences shrdPref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    //pref file name
    private static final String PREF_NAME = "dolphinPref";
    private static final String IS_LOGIN = "IsLoggnedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    public SessionManager(Context context){
        this._context = context;
        shrdPref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = shrdPref.edit();
    }

    public void createLoginSession(String name, String email){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, shrdPref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, shrdPref.getString(KEY_EMAIL, null));
        return  user;
    }

}
