package com.example.tarun.moodle;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tarun on 18/2/16.
 */
public class NotificationAdapter extends BaseAdapter {

    private Context mycontext;
    private ArrayList<Data_model_notification> myarray;
    private LayoutInflater mLayoutInflater = null;


    NotificationAdapter() {
        mycontext = null;
        myarray = null;

    }

    public NotificationAdapter(Context context, JSONObject values) {

        this.mycontext = context;

        JSONArray values1 = null;
        try {
            values1 = values.getJSONArray("notifications");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        myarray = Data_model_notification.fromJson(values1);

    }

    public void remove_from_array(int position){
        myarray.remove(position);
        this.notifyDataSetChanged();
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
    public int getCount() {
        return myarray.size();
    }


    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public Object getItem(int arg) {
        return arg;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){

        View myview = view;
        if(myview==null) {
            LayoutInflater inflater = (LayoutInflater) mycontext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myview = (View) inflater.inflate(R.layout.list_navigation, null);

        }
        TextView name = (TextView) myview.findViewById(R.id.notification_person_name);
        TextView date=(TextView) myview.findViewById(R.id.notification_person_date);
        TextView course = (TextView) myview.findViewById(R.id.notification_course);


        CheckBox box = (CheckBox) myview.findViewById(R.id.is_seen);

        Data_model_notification item = myarray.get(position);
        name.setText(item.person_name);
        date.setText(item.date);
        course.setText(item.course);
            return myview;

    }
}
