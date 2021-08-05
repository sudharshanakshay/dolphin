package com.example.dolphin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dolphin.model.ListPost;

import java.util.ArrayList;
import java.util.List;

public class Post extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListPost> listPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listPosts = new ArrayList<>();

        for(int i=0; i<10; i++){
            ListPost listPost = new ListPost(
                    "hello" + (i+1),
                    "this dolphin app test"
            );

            listPosts.add(listPost);
        }

        adapter = new DataAdapter(listPosts, this);
        recyclerView.setAdapter(adapter);
    }
}