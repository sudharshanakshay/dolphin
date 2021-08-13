package com.example.dolphin;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dolphin.model.Post;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class PostPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Post> listOfPostPages, feeds;
    RequestQueue requestQueue;
    final protected String url = "http://a70c8ac2e069.ngrok.io/feed.php";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        SessionManager sessionManager = new SessionManager(context);
//        sessionManager.checkLogin();


        listOfPostPages = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Post listPostPage = new Post("supporting url", String.valueOf(i + 1), "hello description");
//            System.out.println(listPo());
            listOfPostPages.add(listPostPage);
        }
        fetchFeed();
        System.out.println(listOfPostPages.get(0).getCaption());

        recyclerView.setAdapter(adapter);

    }
    public void fetchFeed(){
        feeds = new ArrayList<Post>();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(context, "couldn't fetch, try again later", Toast.LENGTH_SHORT).show();
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
                            adapter = new DataAdapter(feeds, getApplicationContext());

                            System.out.println("--------------------------");
                            System.out.println(feeds.size());
                            System.out.println(feeds.get(0).getCaption());
                        } catch (JSONException e) {
                            System.out.println("---------------error------------");
                            e.printStackTrace();
                        }
                        System.out.println(feeds.get(0).getCaption());


                        // refreshing recycler view

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("e", "Error: " + error.getMessage());
                        Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
//        System.out.println(feeds.get(0).getCaption());
        requestQueue.add(request);
        //MyApplication.getInstance().addToRequestQueue(request);
    }
}