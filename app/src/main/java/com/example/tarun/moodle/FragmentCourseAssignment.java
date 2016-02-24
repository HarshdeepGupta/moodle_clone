package com.example.tarun.moodle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONObject;


public class FragmentCourseAssignment extends Fragment {

    ListView listView;
    JSONObject assignmentData;
    CourseAssignmentAdapter adapter;

    public FragmentCourseAssignment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CoursePage activity = (CoursePage) getActivity();
        if(activity.get_Assignment_data() == null){
            Log.i("hagga","empty assignment array");
        }
        assignmentData = activity.get_Assignment_data();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        v = inflater.inflate(R.layout.fragment_course_assignment, container, false);
        listView = (ListView) v.findViewById(R.id.course_assignment_list_view);
        adapter = new CourseAssignmentAdapter(this.getContext(),assignmentData);
        listView.setAdapter(adapter);

        return v;
    }
}
