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
 * Created by hd on 21/2/16.
 */
public class CourseGradesAdapter extends BaseAdapter {

    //Variables
    private ArrayList<Data_model_course_grades> gradesData;
    Context context;

    //Methods

    public CourseGradesAdapter(Context context, JSONObject values) {

        JSONArray values1 = null;
        try {
            values1 = values.getJSONArray("grades");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        gradesData = Data_model_course_grades.fromJson(values1);
        this.context = context;
    }

    @Override
    public int getCount() {
        return gradesData.size();
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
            view = inflater.inflate(R.layout.element_course_grade,parent,false);
        }

        TextView score = (TextView) view.findViewById(R.id.score_text_view);
        TextView weight = (TextView) view.findViewById(R.id.weight_text_view);
        TextView absolute_marks = (TextView) view.findViewById(R.id.absolute_marks_text_view);
        TextView grade_item = (TextView) view.findViewById(R.id.grade_item_text_view);

        Data_model_course_grades item =  gradesData.get(position);

        weight.setText(String.valueOf(item.weightage)) ;
        score.setText(String.valueOf(item.score));
        grade_item.setText(item.gradeItem);
        absolute_marks.setText(String.valueOf(item.score*item.weightage / item.out_of));
        return view;
    }





}
