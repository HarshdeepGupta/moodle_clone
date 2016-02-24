package com.example.tarun.moodle;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ThreadDetails extends AppCompatActivity {


    Globals global;
    static String serverAddress;
    static RequestQueue myQueue;
    CommentAdapter comment_Adapter;
    LinearLayout add_comment_layout;
    JSONObject data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        global = (Globals) this.getApplication();
        serverAddress = global.getServerAddress();
        myQueue =  global.getVolleyQueue();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String thread_data,comment_data;
        thread_data = bundle.getString("THREAD_INFO");
        comment_data = bundle.getString("COMMENT_LIST");

        JSONArray comment_list = null;
        try{

                comment_list = new JSONArray(comment_data);
                data = new JSONObject(thread_data);

        }catch (JSONException e) {
            e.printStackTrace();
        }



        comment_Adapter = new CommentAdapter(this,comment_list);
        ListView comment_listview = (ListView) findViewById(R.id.comment_list_drawer);
        comment_listview.setAdapter(comment_Adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_comment_layout = (LinearLayout) findViewById(R.id.add_comment);
                final ListView list = (ListView) findViewById(R.id.comment_list_drawer);


                add_comment_layout.setVisibility(View.VISIBLE);
                //open the keyboard as well for the user

                //

                final Context context = getApplicationContext();

                final int duration = Toast.LENGTH_LONG;
                final Button button = (Button) findViewById(R.id.post_button);
                final EditText comment = (EditText) findViewById(R.id.comment_description);
                final InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                comment.requestFocus();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String comment_description = comment.getText().toString();
                        comment_description.replaceAll("^[ ]*$","%20");
                        Log.i("hagga",comment_description);
                        String name = global.getName();
                        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String date = sdf.format(new Date());

                        final JSONObject object = new JSONObject();
                        try {
                            object.put("comment_name", name);
                            object.put("comment_description",comment_description);
                            object.put("comment_created",date);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        String thread_id = "0";
                        try{

                            thread_id = data.getString("thread_number");

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String url_notification = serverAddress.concat("/threads/post_comment.json?thread_id=").concat(thread_id).concat("&description=").concat(comment_description);
                        Log.i("hagga",url_notification);
                        final JsonObjectRequest sr2 = new JsonObjectRequest(Request.Method.GET,url_notification,null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Toast toast = Toast.makeText(context,"Comment Added", duration);
                                toast.show();
                                comment_Adapter.add_comment(object);
                                add_comment_layout.setVisibility(View.INVISIBLE);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast toast = Toast.makeText(context, "Error3" + error.getMessage(), duration);
                                toast.show();
                            }
                        }) ;
                        myQueue.add(sr2);
                        imm.hideSoftInputFromWindow(comment.getWindowToken(), 0);

                    }

                });

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        Log.i("hagga","here0");
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Log.i("hagga","here1");
            if(add_comment_layout.getVisibility()==View.VISIBLE){
                Log.i("hagga","here2");
                add_comment_layout.setVisibility(View.INVISIBLE);
            }
            else if(add_comment_layout.getVisibility()!=View.VISIBLE){
                Log.i("hagga","kuch_bhi");
                return super.onKeyDown(keyCode, event);
            }

        }
        return super.onKeyDown(keyCode,event);
    }
    */

}
