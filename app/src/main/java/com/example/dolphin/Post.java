package com.example.dolphin;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dolphin.model.ListItem;
import com.example.dolphin.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class Post extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listOfPosts;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        SessionManager sessionManager = new SessionManager(context);
        sessionManager.checkLogin();


        listOfPosts = new ArrayList<>();

        for(int i=0; i<10; i++){
            ListItem listPost = new ListItem(
                    "hello" + (i+1),
                    "this dolphin app test"
            );
            System.out.println(listPost.getHead());
            listOfPosts.add(listPost);
        }

        adapter = new DataAdapter(listOfPosts, this);
        recyclerView.setAdapter(adapter);
    }
}