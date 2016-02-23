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
 * Created by hd on 22/2/16.
 */
public class CourseAssignmentAdapter extends BaseAdapter {

    private ArrayList<Data_model_course_assignment> assignmentData;
    private Context context;


    public CourseAssignmentAdapter(Context context,JSONObject values){

        JSONArray values1 = null;
        try {
            values1 = values.getJSONArray("assignments");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assignmentData = Data_model_course_assignment.fromJson(values1);
        this.context = context;

    }

    @Override
    public int getCount() {
        return assignmentData.size();
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
        View view  = convertView;
        if(view == null ){
            view = inflater.inflate(R.layout.element_course_assignment,parent,false);
        }

        TextView deadline = (TextView) view.findViewById(R.id.assignment_deadline);
        TextView late_days = (TextView) view.findViewById(R.id.assignment_late_days_allowed);
        TextView name = (TextView) view.findViewById(R.id.assignment_name);
        TextView description = (TextView) view.findViewById(R.id.assignment_description);

        Data_model_course_assignment item = assignmentData.get(position);

        deadline.setText(item.deadline);
        late_days.setText(String.valueOf(item.late_days_allowed));
        name.setText(item.name);
        description.setText(item.description);
        return view;

    }
}








