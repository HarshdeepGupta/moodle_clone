package com.example.tarun.moodle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONObject;


public class FragmentCourseGrades extends Fragment {

    private ListView listView;
    JSONObject grades_data;

    public FragmentCourseGrades() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.i("hagga", "onCreate called from FCG");

        CoursePage activity = (CoursePage) getActivity();
        if(activity.get_Grades_data() == null){
            Log.i("hagga","empty grades array");
        }
        grades_data = activity.get_Grades_data();
        Log.i("hagga", "onCreate finished from FCG");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("hagga", "OncreateView called from FCG");
        View view;
        view = inflater.inflate(R.layout.fragment_course_grades, container, false);

        listView = (ListView) view.findViewById(R.id.course_grades_list_view);

        CourseGradesAdapter adapter = new CourseGradesAdapter(this.getContext(), grades_data);

        listView.setAdapter(adapter);

        Log.i("hagga", "OncreateView finished from FCG");

        return view;
    }
}
