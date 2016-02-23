package com.example.tarun.moodle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ThreadDetails extends AppCompatActivity {


    Globals global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        global = (Globals) this.getApplication();

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

        final CommentAdapter comment_Adapter = new CommentAdapter(this,comment_list);
        ListView comment_listview = (ListView) findViewById(R.id.comment_list_drawer);
        comment_listview.setAdapter(comment_Adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout comment_layout = (LinearLayout) findViewById(R.id.add_comment);
                comment_layout.setVisibility(View.VISIBLE);
                final EditText comment_description = (EditText) findViewById(R.id.comment_description);
                Button button = (Button) findViewById(R.id.post_button);
                final JSONObject object = new JSONObject();
                Log.i("hagga","here1");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String time_date = DateFormat.getDateTimeInstance().format(new Date());
                        String description = comment_description.getText().toString();
                        String email = global.getEmail();
                        String name = global.getName();
                        String entry_no = global.getEntry_number();

                        try {
                            object.put("comment_edited", time_date);
                            object.put("comment_description", description);
                            object.put("comment_created", time_date);
                            object.put("comment_name", name);
                            object.put("comment_entry", entry_no);
                            object.put("comment_email", email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

                comment_Adapter.add(object);

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
