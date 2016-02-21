package com.example.tarun.moodle;

import android.content.Context;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCourseGrades.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentCourseGrades#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCourseGrades extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView listView ;

    //values is the JSON array that we receive from the server
    String[] values;





    // TODO: Rename and change types of parameters



    private OnFragmentInteractionListener mListener;

    public FragmentCourseGrades() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 JSON array og Grades     *
     * @return A new instance of fragment FragmentCourseGrades.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCourseGrades newInstance(String[] param1) {
        Log.i("hagga","newInstance called from FCG");
        FragmentCourseGrades fragment = new FragmentCourseGrades();
        Bundle args = new Bundle();
        args.putStringArray(ARG_PARAM1, param1);

        fragment.setArguments(args);
        Log.i("hagga", "newIntance finished from FCG");
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("hagga", "onCreate called from FCG");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            values = getArguments().getStringArray(ARG_PARAM1);
        }
        values = new String[1];
        values[0] = "harshdeep";
        listView = (ListView) getActivity().findViewById(R.id.course_grades_list_view);
//        listView.setAdapter(new CourseGradesAdapter(getContext(),values));
        Log.i("hagga", "onCreate finished from FCG");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_grades, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("hagga", "onAttach called from FCG");
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
