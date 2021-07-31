package com.example.dolphin.session;

import android.content.Context;
import android.content.SharedPreferences;

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

}
