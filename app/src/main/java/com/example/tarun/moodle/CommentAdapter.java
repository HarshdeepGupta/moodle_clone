package com.example.tarun.moodle;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tarun on 19/2/16.
 */
public class CommentAdapter extends BaseAdapter{

    private Context mycontext;
    private JSONArray myarray;
    private LayoutInflater mLayoutInflater = null;
    private JSONArray myarrayreverse;

    CommentAdapter() {
        mycontext = null;
        myarray = null;

    }

    public CommentAdapter(Context c,JSONArray comment) {
        mycontext = c;
        myarray = comment;
        myarrayreverse = new JSONArray();
        for (int i=myarray.length()-1;i>=0;i--){
            try {
                myarrayreverse.put(myarray.getJSONObject(i));
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
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

    @Override
    public Object getItem(int arg) {

        return arg;
    }

    public void add_comment(JSONObject object){
        myarray.put(object);
        myarrayreverse = null;
        myarrayreverse = new JSONArray();
        for (int i=myarray.length()-1;i>=0;i--){
            try {
                myarrayreverse.put(myarray.getJSONObject(i));
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){

        View myview = view;


        if(myview==null) {
            LayoutInflater inflater = (LayoutInflater) mycontext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myview = inflater.inflate(R.layout.list_comments, null);

        }
        TextView name = (TextView) myview.findViewById(R.id.notification_person_name);
        TextView date=(TextView) myview.findViewById(R.id.notification_person_date);
        TextView comment_description = (TextView) myview.findViewById(R.id.thread_comment);
        comment_description.setMovementMethod(new ScrollingMovementMethod());

        try {
            name.setText(myarrayreverse.getJSONObject(position).getString("comment_name"));
            date.setText(myarrayreverse.getJSONObject(position).getString("comment_created"));
            comment_description.setText(myarrayreverse.getJSONObject(position).getString("comment_description"));

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return myview;

    }

}
