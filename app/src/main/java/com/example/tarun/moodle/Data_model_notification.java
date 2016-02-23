package com.example.tarun.moodle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tarun on 23/2/16.
 */
public class Data_model_notification {

        public String person_name;
        public String date;
        public String course;
        public int notification_id;
        private boolean is_seen = false;

        public Data_model_notification(String person_name,String date,String course) {
            this.person_name = person_name;
            this.date= date;
            this.course=course;
        }

        public Data_model_notification(JSONObject object) {


            try {
                this.date = object.getString("created_at");
                this.course = object.getString("course");
                this.person_name  = object.getString("name");
                this.notification_id = Integer.parseInt(object.getString("notification_id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public static ArrayList<Data_model_notification> fromJson(JSONArray jsonObjects) {


            ArrayList<Data_model_notification> gradesData = new ArrayList<Data_model_notification>();
            for (int i = 0; i < jsonObjects.length(); i++) {
                try {
                    gradesData.add(new Data_model_notification(jsonObjects.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return gradesData;
        }

        public void set_is_seen(boolean set_value){
            this.is_seen = set_value;
        }

        public boolean get_is_seen(){
            return this.is_seen;
        }


    }
