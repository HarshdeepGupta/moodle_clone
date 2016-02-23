package com.example.tarun.moodle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hd on 22/2/16.
 */
public class FragmentCourseThreads extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";

    public FragmentCourseThreads() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//            Log.i("hagga", "newInstance called");
        FragmentCourseThreads fragment = new FragmentCourseThreads();
//            Log.i("hagga", "NewInstance finished");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course_thread, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }
}

