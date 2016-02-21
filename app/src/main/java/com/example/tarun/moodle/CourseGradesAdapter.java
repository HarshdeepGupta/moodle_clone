package com.example.tarun.moodle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by hd on 21/2/16.
 */
public class CourseGradesAdapter extends ArrayAdapter<String> {


    private final Context context;
    private final String[] values;


    //values is a string containing data in JSON format

    public CourseGradesAdapter(Context context, String[] values) {
        super(context, -1,values);
        this.context = context;
        this.values = values;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.course_grade_element, parent, false);
        TextView score = (TextView) rowView.findViewById(R.id.score_text_view);
        TextView weight = (TextView) rowView.findViewById(R.id.weight_text_view);
        TextView absolute_marks = (TextView) rowView.findViewById(R.id.absolute_marks_text_view);


        //convert values into a JSON object

//        try {
//            JSONArray data = new JSONArray(values);
//        }
//        catch (JSONException e){
//            Log.i("hagga",e.toString());
//        }

        //TODO by HD
        //Parse JSON objects and set the values of this row

//        textView.setText(values[position]);
//        // change the icon for Windows and iPhone
//        String s = values[position];
//        if (s.startsWith("iPhone")) {
//            imageView.setImageResource(R.drawable.no);
//        } else {
//            imageView.setImageResource(R.drawable.ok);
//        }

        return rowView;
    }



}
