package com.example.tarun.moodle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.DocumentsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;




public class Login extends AppCompatActivity {

    static String serverAddress;
    static RequestQueue myQueue;
    String[] course_array;
    JSONArray notification_array;
    String first_name;
    String last_name;
    String entry_number;
    static boolean proceed;

    EditText user ;
    EditText pass ;

    Globals global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        proceed = false;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Moodle");


        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);

        manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        global = (Globals) this.getApplication();

        global.setServerAddress("http://192.168.0.106:8000");

        serverAddress = ((Globals) this.getApplication()).getServerAddress();
        Log.i("hagga", serverAddress);

        myQueue = Volley.newRequestQueue(this);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        user.setText("cs5110281");
        pass.setText("jasmeet");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login_method(View view){

        final Context context = getApplicationContext();

        final int duration = Toast.LENGTH_LONG;


        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        String user_name = user.getText().toString();
        String password = pass.getText().toString();


        String url = serverAddress.concat("/default/login.json?userid=");

        url = url.concat(user_name).concat("&password=").concat(password);




        String url_notification = serverAddress.concat("/default/notifications.json");
        final JsonObjectRequest sr2 = new JsonObjectRequest(Request.Method.GET,url_notification,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    notification_array = new JSONArray();
                    JSONArray notify = response.getJSONArray("notifications");

                    for (int i=0;i<notify.length();i++){

                        JSONObject object = new JSONObject();
                        String created = notify.getJSONObject(i).getString("created_at");
                        String description = notify.getJSONObject(i).getString("description");
                        String name,course;
                        String thread_link;
                        Document doc = Jsoup.parse(description);
                        org.jsoup.select.Elements links = doc.select("a");



                        name = links.get(0).text();
                        course = links.get(2).text();
                        thread_link = links.get(1).attr("href");
                        object.put("created_at", created);
                        object.put("course", course);
                        object.put("name", name);
                        object.put("thread_link", thread_link);
                        notification_array.put(object);

                    }
                    successCallback();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, "Error3" + error.getMessage(), duration);
                toast.show();
            }
        }) ;


        String url_courses = serverAddress.concat("/courses/list.json");

        final JsonObjectRequest sr1 = new JsonObjectRequest(Request.Method.GET,url_courses,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray courses = response.getJSONArray("courses");

                    course_array = new String[courses.length()];
                    for (int i=0;i<courses.length();i++){
                        course_array[i] = courses.getJSONObject(i).getString("code");
                    }
                    myQueue.add(sr2);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, "Error2" + error.getMessage(), duration);
                toast.show();
            }
        }) ;




        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                String message;
                message = response.toString();

                try {
                    proceed = response.getBoolean("success");
                    if(proceed==false){
                        Toast toast = Toast.makeText(context, "Invalid Username or Password", duration);
                        toast.show();
                    }
                    else if(proceed==true){
                        JSONObject details = new JSONObject();
                        details = response.getJSONObject("user");
                        first_name = details.getString("first_name");
                        last_name = details.getString("last_name");
                        entry_number = details.getString("entry_no");
                        String email = details.getString("email");
                        global.setName(first_name.concat(" ").concat(last_name));
                        global.setEmail(email);
                        global.setEntry_number(entry_number);
                        myQueue.add(sr1);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, "Error1" + error.getMessage(), duration);
                toast.show();
            }
        }) ;
        myQueue.add(sr);



    }


    public void successCallback() {
        Intent intent = new Intent(this,home.class);
        Bundle bundle = new Bundle();
        bundle.putString("FIRST_NAME", first_name);
        bundle.putString("LAST_NAME", last_name);
        bundle.putString("ENTRY_NUMBER", entry_number);
        bundle.putStringArray("COURSE_LIST", course_array);
        bundle.putString("NOTIFICATION_LIST", notification_array.toString());
        intent.putExtras(bundle);
        startActivity(intent);
        //finish(); Dont use this

    }



}
