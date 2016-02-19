package com.example.tarun.moodle;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static String serverAddress;
    static RequestQueue myQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle userinfo = intent.getExtras();
        String username = userinfo.getString("FIRST_NAME").concat(" ").concat(userinfo.getString("LAST_NAME"));
        String userentry = userinfo.getString("ENTRY_NUMBER");

        //Parsing the course array and the notifications array
        String[] course_array = userinfo.getStringArray("COURSE_LIST");
        String notification;
        notification = userinfo.getString("NOTIFICATION_LIST");
        JSONArray notificationlist = null;
        try {
            notificationlist = new JSONArray(notification);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        //--------------parse notification_list-------------//


        //---------to contain the full description----------------//

        /*--------------------Networking--------------------------*/

        final Context context = getApplicationContext();
        final int duration = Toast.LENGTH_LONG;


        serverAddress = ((Globals) this.getApplication()).getServerAddress();;

        myQueue = Volley.newRequestQueue(this);





        /*--------------------Networking--------------------------*/

        /*------------------Initialize UI--------------------------*/

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


        //populate the listview in the drawer layout with the course list

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView mDrawerList = (ListView) findViewById(R.id.course_list_drawer);



        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_text, course_array));
        // Set the list's click listener
        //mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


        ListView notification_listview = (ListView) findViewById(R.id.notification_list_drawer);
        notification_listview.setAdapter(new NotificationAdapter(this, notificationlist));
        notification_listview.setOnItemClickListener(new notificationitemclick(this,notificationlist));



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        //To show user name and entry number in the navigation Drawer
        TextView name = (TextView) findViewById(R.id.nav_header_name);
        TextView entry = (TextView) findViewById(R.id.nav_header_entry);




        /*
        name.setText(username);
        Log.i("hagga", "here7");
        entry.setText(userentry);
        Log.i("hagga", "here8");
        /*
        /*------------------Initialize UI--------------------------*/
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void navDrawerClickListener(View view) {

        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(getApplicationContext(), "Success !", duration);
        toast.show();

        Log.i("hagga","Here7");


    }
}
