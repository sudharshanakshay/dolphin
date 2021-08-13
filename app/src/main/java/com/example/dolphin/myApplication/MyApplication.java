package com.example.dolphin.myApplication;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {
    public static final String TAG = MyApplication.class
            .getSimpleName();

    private RequestQueue requestQueue;

    private static MyApplication myAppInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        myAppInstance = this;
    }

    public static synchronized MyApplication getInstance(){
        return myAppInstance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request request){
            request.setTag(TAG);
            getRequestQueue().add(request);
    }

}
