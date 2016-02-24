package com.example.tarun.moodle;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hd on 23/2/16.
 */
public class Data_model_course_threads {

    public String thread_title;
    public String thread_description;
    public String thread_created_at;
    public String thread_last_updated;
    public int thread_id;


    public Data_model_course_threads(String thread_title,String thread_description,String thread_created_at,String thread_last_updated){
        this.thread_title = thread_title;
        this.thread_description = thread_description;
        this.thread_last_updated = thread_last_updated;
        this.thread_created_at = thread_created_at;
    }

    public Data_model_course_threads(JSONObject object) {

        try {
            this.thread_title = object.getString("title");
            this.thread_description = object.getString("description");
            this.thread_created_at  = object.getString("created_at");
            this.thread_last_updated = object.getString("updated_at");
            this.thread_id = object.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<Data_model_course_threads> fromJson(JSONArray jsonObjects) {


        ArrayList<Data_model_course_threads> threadsData = new ArrayList<Data_model_course_threads>();
        int length = jsonObjects.length();
        for (int i = 0; i < length; i++) {
            try {
                threadsData.add(new Data_model_course_threads(jsonObjects.getJSONObject(length-i-1)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return threadsData;
    }




}
