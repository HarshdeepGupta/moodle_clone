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







    public Data_model_course_threads(JSONObject object) {

//        Log.i("hagga", "Datamodel Constructor Called");
        try {
            this.thread_title = object.getString("title");
            this.thread_description = object.getString("description");
            this.thread_created_at  = object.getString("created_at");
            this.thread_last_updated = object.getString("updated_at");
            this.thread_id = object.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        Log.i("hagga", "Datamodel Constructor Finished");
    }


    public static ArrayList<Data_model_course_threads> fromJson(JSONArray jsonObjects) {

//        Log.i("hagga", "FromJSONcalled" );
        ArrayList<Data_model_course_threads> threadsData = new ArrayList<Data_model_course_threads>();
        int length = jsonObjects.length();
        for (int i = 0; i < length; i++) {
            try {
                threadsData.add(new Data_model_course_threads(jsonObjects.getJSONObject(length-i-1)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//        Log.i("hagga", "FromJSONFinished");

        return threadsData;
    }




}
