package com.example.tarun.moodle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

/**
 * Created by hd on 21/2/16.
 */
public class CourseGradesAdapter extends ArrayAdapter<Data_model_course_grades> {

    //Variables
    private ArrayList<Data_model_course_grades> gradesData;

    //Methods

    public CourseGradesAdapter(Context context, JSONObject values) {

        super(context, 0);
        Log.i("hagga","gradesAdapterConstructorCalled");
        JSONArray values1 = null;
        try {
            values1 = values.getJSONArray("grades");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        gradesData = Data_model_course_grades.fromJson(values1);
        Log.i("hagga","gradesAdapterConstructorFinished");

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i("hagga", "gradesAdapterGetviewCalled");
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //make sure we have a view to work with
        if(convertView == null ){
            convertView = inflater.inflate(R.layout.course_grade_element,parent,false);
            Log.i("hagga","ViewInflated");
        }

        TextView score = (TextView) convertView.findViewById(R.id.score_text_view);
        TextView weight = (TextView) convertView.findViewById(R.id.weight_text_view);
        TextView absolute_marks = (TextView) convertView.findViewById(R.id.absolute_marks_text_view);
        TextView grade_item = (TextView) convertView.findViewById(R.id.grade_item_text_view);

        Data_model_course_grades item =  gradesData.get(position);

        weight.setText(item.weightage) ;
        score.setText(item.score);
        grade_item.setText(item.gradeItem);
        absolute_marks.setText(item.score*item.weightage / item.out_of);

        Log.i("hagga", "gradesAdapterGetviewFinished");

        return convertView;
    }



    @Override
    public boolean areAllItemsEnabled (){
        return true;
    }
    @Override
    public int getItemViewType (int position){
        return 0;
    }

    @Override
    public int getViewTypeCount(){
        return 1;
    }
    @Override
    public long getItemId(int position) {

        return position;
    }

}
