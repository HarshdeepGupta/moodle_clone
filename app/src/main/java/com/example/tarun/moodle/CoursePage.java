package com.example.tarun.moodle;


import android.content.Intent;
import android.content.Context;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class CoursePage extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private String courseCode;
    private String serverAddress;
    private String assignmentUrl,threadsUrl,gradesUrl;
    private static RequestQueue myQueue;
    private JSONObject assignment_data;
    private JSONObject threads_data;
    private JSONObject grades_data;

    //Methods for making network requests
    private void getAssignmentsFromServer() {

        final JsonObjectRequest sr1 = new JsonObjectRequest(Request.Method.GET,assignmentUrl,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                Log.i("hagga",response.toString());
                assignment_data = response;
//                Toast toast = Toast.makeText(getApplicationContext(), "Response 1 Received" , Toast.LENGTH_LONG);
//                toast.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error in fetching assignment list" + error.getMessage(), Toast.LENGTH_LONG);
                toast.show();
            }
        }) ;

        myQueue.add(sr1);


    }

    private void getThreadsFromServer() {

        final JsonObjectRequest sr2 = new JsonObjectRequest(Request.Method.GET,threadsUrl,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                Log.i("hagga",response.toString());
                threads_data = response;
//                Toast toast = Toast.makeText(getApplicationContext(), "Response 2 Received" , Toast.LENGTH_LONG);
//                toast.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error in fetching threads list" + error.getMessage(), Toast.LENGTH_LONG);
                toast.show();
            }
        }) ;

        myQueue.add(sr2);


    }

    private void getGradesFromServer() {

        final JsonObjectRequest sr3 = new JsonObjectRequest(Request.Method.GET,gradesUrl,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                Log.i("hagga",response.toString());
//                Toast toast = Toast.makeText(getApplicationContext(), "Response 3 Received" , Toast.LENGTH_LONG);
//                toast.show();
                grades_data = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error in fetching grades list" + error.getMessage(), Toast.LENGTH_LONG);
                toast.show();
            }
        }) ;

        myQueue.add(sr3);


    }

    private void getDataFromServer(){
        getThreadsFromServer();
        getAssignmentsFromServer();
        getGradesFromServer();
    }


    public JSONObject get_Grades_data() {
        return grades_data;
    }

    public JSONObject get_Threads_data() {
        return threads_data;
    }

    public JSONObject get_Assignment_data() {
        return assignment_data;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.i("hagga", "Called onCreate");


        Intent intent = getIntent();

        //Initialize the Variables
        courseCode = intent.getStringExtra("CourseCode");
        serverAddress = ((Globals) this.getApplication()).getServerAddress();
        assignmentUrl = serverAddress.concat("/courses/course.json/").concat(courseCode).concat("/assignments");
        gradesUrl = serverAddress.concat("/courses/course.json/").concat(courseCode).concat("/grades");
        threadsUrl = serverAddress.concat("/courses/course.json/").concat(courseCode).concat("/threads");
        myQueue = ((Globals) this.getApplication()).getVolleyQueue();

        //Make Server Requests to get the data
        getDataFromServer();

        setContentView(R.layout.activity_course_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set up the ViewPager with the sections adapter.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //restores session on app closure
        SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course_page, menu);
        return true;
    }


    //handling the up functionality
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.

            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = new FragmentCourseThreads();
                    break;
                case 1:
                    fragment = new FragmentCourseAssignment();
                    break;
                case 2:
                    fragment = new FragmentCourseGrades();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Discussions";
                case 1:
                    return "Assignments";
                case 2:
                    return "Grades";
            }
            return null;
        }
    }
}