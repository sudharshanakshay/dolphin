package com.example.dolphin.ui.home;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dolphin.DataAdapter;
import com.example.dolphin.R;
import com.example.dolphin.model.Post;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    final protected String url = "http://75542322e05c.ngrok.io/feed.php";

    RecyclerView home_recyclerView;
    List<Post> feeds= new ArrayList<Post>();
    DataAdapter myAdapter;
    RequestQueue requestQueue;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        requestQueue = Volley.newRequestQueue(getContext());

        //SessionManager sessionManager = new SessionManager(context);
        //sessionManager.checkLogin();


        home_recyclerView = view.findViewById(R.id.home_recyclerView);
        home_recyclerView.setHasFixedSize(true);
        home_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadRecycleViewData();
        return view;
    }

    public void loadRecycleViewData(){
        ProgressDialog progressDialog = new ProgressDialog(getContext());
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
                        try {
                            for(int i=0; i<response.length(); i++){
                                Post post = new Post(
                                        response.getJSONArray(i).getString(0),
                                        response.getJSONArray(i).getString(1),
                                        response.getJSONArray(i).getString(2)
                                );
                                feeds.add(post);
                            }
                            myAdapter = new DataAdapter(feeds, getContext());
                            home_recyclerView.setAdapter(myAdapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    public void fetchFeed() {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getActivity(), "couldn't fetch, try again later", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        JSONArray jsonArray = new JSONArray();

                        //Type collectionType = new TypeToken<List<Post>>(){}.getType();

                        //List<Post> items = new Gson().fromJson(response.toString(),collectionType);
                        //feeds.clear();
                       // feeds.addAll(items);
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                jsonArray = response.getJSONArray(i);
                                Post post = new Post(/*jsonArray.getString(0),*/
                                        jsonArray.getString(0),
                                        jsonArray.getString(1),
                                        jsonArray.getString(2));
                                feeds.add(post);
                            }

                            System.out.println("--------------------------");
                            System.out.println(feeds.size());

                            System.out.println(feeds.iterator());
                            System.out.println(feeds.get(1).getCaption());
                        } catch (JSONException e) {
                            System.out.println("---------------error------------");
                            e.printStackTrace();
                        }


                        // refreshing recycler view

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("e", "Error: " + error.getMessage());
                        Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(request);
    }


}