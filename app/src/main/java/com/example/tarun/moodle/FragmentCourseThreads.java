package com.example.tarun.moodle;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hd on 22/2/16.
 */
public class FragmentCourseThreads extends Fragment{

    ListView listview;
    JSONObject threadsData;
    CourseThreadAdapter adapter;
    String courseCode;
    Globals global;
    String serverAddress;
    RequestQueue myQueue;


    public FragmentCourseThreads() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CoursePage activity = (CoursePage) getActivity();
        if(activity.get_Grades_data() == null){
            Log.i("hagga", "empty grades array");
        }
        threadsData = activity.get_Threads_data();
        this.courseCode = activity.courseCode;
        global = ((Globals) activity.getApplication());
        serverAddress = global.getServerAddress();
        myQueue = global.getVolleyQueue();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_course_thread, container, false);
        listview = (ListView)view.findViewById(R.id.course_thread_list_view);
        adapter = new CourseThreadAdapter(getContext(),threadsData);
        listview.setAdapter(adapter);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {


//                TextView tvCountry = (TextView) linearLayoutChild.getChildAt(0);
                ArrayList<Data_model_course_threads> threadsData = adapter.getThreadsData();
                int thread_id = threadsData.get(position).thread_id;

                TextView t = new TextView(getContext());
                t.setText("This is it");
                View v = getActivity().findViewById()

                Toast.makeText(getContext(), "Implement ThreadClick method", Toast.LENGTH_LONG).show();
            }
        };

        listview.setOnItemClickListener(itemClickListener);

        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.add_thread_layout);
        final TextView add_thread_title = (TextView) view.findViewById(R.id.add_thread_title);
        final TextView add_thread_description = (TextView) view.findViewById(R.id.add_thread_description);
        final Button button = (Button) view.findViewById(R.id.add_thread_button);




                FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_add_thread);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                linearLayout.setVisibility(View.VISIBLE);


                button.setOnClickListener(new View.OnClickListener (){
                    @Override
                    public void onClick(View v){

                        String title = (String) add_thread_title.getText().toString();
                        String description = (String) add_thread_description.getText().toString();
                        String date = "Now";
                        final Data_model_course_threads newThread = new Data_model_course_threads(title,description,date,date);

                        String url = serverAddress.concat("/threads/new.json?title=").concat(title).
                                concat("&description=").concat(description).concat("&course_code=").concat(courseCode);

                        final JsonObjectRequest sr1 = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                Log.i("hagga", response.toString());
                                linearLayout.setVisibility(View.INVISIBLE);
                                add_thread_title.setText("");
                                add_thread_description.setText("");
                                Snackbar.make(getView(), "Thread Added", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                                adapter.add(newThread);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Error in fetching assignment list" + error.getMessage(), Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }) ;
                        myQueue.add(sr1);
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromInputMethod(add_thread_description.getWindowToken(),0);
                    }
                } );

            }
        });
        return view;
    }


}

