package com.example.tarun.moodle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ThreadDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String thread_data,comment_data;
        thread_data = bundle.getString("THREAD_INFO");
        comment_data = bundle.getString("COMMENT_LIST");

        JSONObject data = null;
        JSONArray comment_list = null;
        try{

                comment_list = new JSONArray(comment_data);
                ListView comment_listview = (ListView) findViewById(R.id.comment_list_drawer);
                comment_listview.setAdapter(new CommentAdapter(this,comment_list));
                data = new JSONObject(thread_data);

        }catch (JSONException e) {
            e.printStackTrace();
        }

        ListView comment_listview = (ListView) findViewById(R.id.comment_list_drawer);
        comment_listview.setAdapter(new CommentAdapter(this,comment_list));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
