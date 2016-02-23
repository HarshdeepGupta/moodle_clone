package com.example.tarun.moodle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by hd on 22/2/16.
 */
public class FragmentCourseThreads extends Fragment{

    ListView listview;
    JSONObject threadsData;
    CourseThreadAdapter adapter;


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




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_course_thread, container, false);
        listview = (ListView)view.findViewById(R.id.course_thread_list_view);
        adapter = new CourseThreadAdapter(getContext(),threadsData);
        listview.setAdapter(adapter);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {


//                TextView tvCountry = (TextView) linearLayoutChild.getChildAt(0);

                Toast.makeText(getContext(), "Implement ThreadClick method", Toast.LENGTH_LONG).show();
            }
        };

        listview.setOnItemClickListener(itemClickListener);



        return view;
    }


}

