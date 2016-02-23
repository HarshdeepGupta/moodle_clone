package com.example.tarun.moodle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hd on 23/2/16.
 */
public class CourseThreadAdapter extends BaseAdapter {



    //Variables
    private ArrayList<Data_model_course_threads> threadsData;
    Context context;

    //Methods

    public CourseThreadAdapter(Context context, JSONObject values) {

        JSONArray values1 = null;
        try {
            values1 = values.getJSONArray("course_threads");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        threadsData = Data_model_course_threads.fromJson(values1);
        this.context = context;
    }

    @Override
    public int getCount() {
        return threadsData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //make sure we have a view to work with
        View view = convertView;
        if(view == null ){
            view = inflater.inflate(R.layout.element_course_thread,parent,false);
        }

        TextView title = (TextView) view.findViewById(R.id.course_thread_title);
        TextView created_at = (TextView) view.findViewById(R.id.course_thread_created_at);
        TextView thread_descrption = (TextView) view.findViewById(R.id.course_thread_description);
        TextView last_updated = (TextView) view.findViewById(R.id.course_thread_last_updated);

        Data_model_course_threads item =  threadsData.get(position);

        title.setText(String.valueOf(item.thread_title)) ;
        created_at.setText(String.valueOf(item.thread_created_at));
        thread_descrption.setText(item.thread_description);
        last_updated.setText(String.valueOf(item.thread_last_updated));
        return view;
    }





}