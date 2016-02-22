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
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;

    ListView listView;
    JSONObject assignmentData;
    CourseAssignmentAdapter adapter;

    public FragmentCourseAssignment() {
        // Required empty public constructor
    }

//    // TODO: Rename and change types and number of parameters
//    public static FragmentCourseAssignment newInstance(String param1, String param2) {
//        FragmentCourseAssignment fragment = new FragmentCourseAssignment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CoursePage activity = (CoursePage) getActivity();
        if(activity.get_Assignment_data() == null){
            Log.i("hagga","empty assignment array");
        }
        assignmentData = activity.get_Assignment_data();
        Log.i("hagga","OnCreate called from FCA");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("hagga","Oncreate view called from FCA");

        View v;
        v = inflater.inflate(R.layout.fragment_course_assignment, container, false);
        listView = (ListView) v.findViewById(R.id.course_assignment_list_view);
        adapter = new CourseAssignmentAdapter(this.getContext(),assignmentData);
        listView.setAdapter(adapter);

        return v;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//        Log.i("hagga","OnButtonPressed called from FCA");
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//        Log.i("hagga","OnAttach called from FCA");
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
