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

/**
 * Created by tarun on 19/2/16.
 */
public class CommentAdapter extends BaseAdapter{

    private Context mycontext;
    private JSONArray myarray;
    private LayoutInflater mLayoutInflater = null;


    CommentAdapter() {
        mycontext = null;
        myarray = null;

    }

    public CommentAdapter(Context c,JSONArray comment) {
        mycontext = c;
        myarray = comment;

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
        Log.i("hagga",String.valueOf(myarray.length()));
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

    @Override
    public View getView(int position, View view, ViewGroup parent){

        View myview = view;


        if(myview==null) {
            LayoutInflater inflater = (LayoutInflater) mycontext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myview = (View) inflater.inflate(R.layout.list_comments, null);

        }
        TextView name = (TextView) myview.findViewById(R.id.thread_comment_name);
        TextView date=(TextView) myview.findViewById(R.id.thread_comment_date);
        TextView comment_description = (TextView) myview.findViewById(R.id.thread_comment);
        comment_description.setMovementMethod(new ScrollingMovementMethod());

        try {
            name.setText(myarray.getJSONObject(position).getString("comment_name"));
            date.setText(myarray.getJSONObject(position).getString("comment_created"));
            comment_description.setText(myarray.getJSONObject(position).getString("comment_description"));

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return myview;

    }

}
