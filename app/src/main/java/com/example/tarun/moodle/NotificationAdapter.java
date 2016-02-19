package com.example.tarun.moodle;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by tarun on 18/2/16.
 */
public class NotificationAdapter extends BaseAdapter {

    private Context mycontext;
    private JSONArray myarray;
    private LayoutInflater mLayoutInflater = null;


    NotificationAdapter() {
        mycontext = null;
        myarray = null;

    }

    public NotificationAdapter(Context c,JSONArray notifications) {
        mycontext = c;
        myarray = notifications;

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
        return myarray.length();
    }


    @Override
    public long getItemId(int position) {

        return position;
    }

    public Object getItem(int arg) {
        return arg;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){

        View myview = view;

        Log.i("hagga",String.valueOf(position));
        if(myview==null) {
            LayoutInflater inflater = (LayoutInflater) mycontext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myview = (View) inflater.inflate(R.layout.list_navigation, null);

        }
        TextView name = (TextView) myview.findViewById(R.id.notification_person_name);
        TextView date=(TextView) myview.findViewById(R.id.notification_person_date);
        TextView course = (TextView) myview.findViewById(R.id.notification_course);


        try {
            name.setText(myarray.getJSONObject(position).getString("name"));
            date.setText(myarray.getJSONObject(position).getString("created_at"));
            course.setText(myarray.getJSONObject(position).getString("course"));


        }catch (JSONException e) {
            e.printStackTrace();
        }
            return myview;

    }
}
