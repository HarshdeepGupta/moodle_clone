package com.example.tarun.moodle;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hd on 22/2/16.
 */
public class Data_model_course_grades {

    public int weightage;
    public int out_of;
    public int score;
    public String gradeItem;


    public Data_model_course_grades(int weightage,int out_of,int score,String gradeItem) {
        this.weightage = weightage;
        this.out_of = out_of;
        this.score = score;
        this.gradeItem = gradeItem;
    }



    public Data_model_course_grades(JSONObject object) {

        Log.i("hagga", "Datamodel Constructor Called");
        try {
            this.weightage = object.getInt("weightage");
            this.out_of = object.getInt("out_of");
            this.score  = object.getInt("score");
            this.gradeItem = object.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Log.i("hagga", this.gradeItem);
        Log.i("hagga", "Datamodel Constructor Finished");
    }


    public static ArrayList<Data_model_course_grades> fromJson(JSONArray jsonObjects) {

        Log.i("hagga", "FromJSONcalled" );
        ArrayList<Data_model_course_grades> gradesData = new ArrayList<Data_model_course_grades>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                gradesData.add(new Data_model_course_grades(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.i("hagga", "FromJSONFinished");

        return gradesData;
    }
}

