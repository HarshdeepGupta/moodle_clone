package com.example.tarun.moodle;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hd on 22/2/16.
 */
public class Data_model_course_assignment {

    public String name;
    public String created_at;
    public int late_days_allowed;
    public String deadline;
    public String description;

    public Data_model_course_assignment(JSONObject object) {

        try {
            this.name = object.getString("name");
            this.created_at = object.getString("created_at");
            this.late_days_allowed  = object.getInt("late_days_allowed");
            this.deadline = object.getString("deadline");
            this.description = object.getString("description");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<Data_model_course_assignment> fromJson(JSONArray jsonObjects) {


        ArrayList<Data_model_course_assignment> assignmentData = new ArrayList<Data_model_course_assignment>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                assignmentData.add(new Data_model_course_assignment(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return assignmentData;
    }
}

