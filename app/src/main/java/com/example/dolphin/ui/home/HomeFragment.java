package com.example.dolphin.ui.home;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.dolphin.DataAdapter;
import com.example.dolphin.R;
import com.example.dolphin.VolleyImageLOoader;
import com.example.dolphin.constants.Constants;
import com.example.dolphin.model.Post;
import com.example.dolphin.session.SessionManager;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    Constants constants = new Constants();
    String url = constants.getFeed_url();

    RecyclerView home_recyclerView;
    List<Post> feeds= new ArrayList<>();
    DataAdapter myAdapter;
    RequestQueue requestQueue;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadRecycleViewData();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //requestQueue = Volley.newRequestQueue(getContext());
        requestQueue = VolleyImageLOoader.getInstance(getContext()).getRequestQueue(); //started requestQueue

        SessionManager sessionManager = new SessionManager();
        sessionManager.setSharedPreference(getContext());
        sessionManager.checkLogin();


        home_recyclerView = view.findViewById(R.id.home_recyclerView);
        home_recyclerView.setHasFixedSize(true);
        home_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadRecycleViewData();
        return view;

    }

    public static Bitmap loadImageFromWeb(String _url){
        InputStream inputStream = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
             inputStream = connection.getInputStream();


        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

    public void loadRecycleViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading data..");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest =  new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getActivity(), "couldn't fetch, try again later", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        int i;
                        try {
                            feeds.clear();
                            for( i=0; i<response.length(); i++){
                                Post post = new Post(
                                        new URL(response.getJSONArray(i).getString(0)),
                                        response.getJSONArray(i).getString(1),
                                        response.getJSONArray(i).getString(2),
                                        response.getJSONArray(i).getString(3)
                                );

                                feeds.add(post);
                                System.out.println(feeds.get(i).getImage_url());
                                System.out.println(feeds.get(i).getCaption());
                                System.out.println(feeds.get(i).getDescription());
                                System.out.println(feeds.get(i).getSupporting_url());
                            }
                            myAdapter = new DataAdapter(feeds, getContext());
                            progressDialog.dismiss();
                            home_recyclerView.setAdapter(myAdapter);
                            System.out.println("---------------------------"+ String.valueOf(i) +"---------------------------");
                        } catch (Exception e) {
                            progressDialog.dismiss();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       progressDialog.dismiss();
                        Toast.makeText(getActivity(), "server error,please try again later", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        //RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        //requestQueue.add(jsonArrayRequest);
        VolleyImageLOoader.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
    }


}