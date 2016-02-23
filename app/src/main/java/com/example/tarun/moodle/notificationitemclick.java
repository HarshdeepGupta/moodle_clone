package com.example.tarun.moodle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tarun on 19/2/16.
 */
public class notificationitemclick implements AdapterView.OnItemClickListener{

    static String serverAddress ="http://192.168.0.106:8000";
    static RequestQueue myQueue;
    final int duration = Toast.LENGTH_LONG;


    private Context mycontext;
    private JSONArray myarray;
    private JSONObject data;
    private JSONArray comment_list;


    public notificationitemclick(Context c,JSONArray j){
        mycontext = c;
        myarray = j;
        myQueue = Volley.newRequestQueue(c);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        String thread_link = "";
        String name = "";
        data = new JSONObject();
        try {
            thread_link = myarray.getJSONObject(position).getString("thread_link");
            thread_link = thread_link.replaceFirst("thread/", "thread.json/");
            name = myarray.getJSONObject(position).getString("name");
            data.put("thread_name",name);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

//        Log.i("hagga",serverAddress.concat(thread_link));
        String url_thread = serverAddress.concat(thread_link);
        final JsonObjectRequest sr1 = new JsonObjectRequest(Request.Method.GET,url_thread,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    comment_list =new JSONArray();
                    JSONObject course = response.getJSONObject("course");
                    JSONArray comments = response.getJSONArray("comments");
                    JSONObject thread_info = response.getJSONObject("thread");
                    JSONArray comment_user = response.getJSONArray("comment_users");
                    JSONArray times_edited = response.getJSONArray("times_readable");
                    data.put("thread_course",course.getString("code"));
                    data.put("thread_title",thread_info.getString("title"));
                    data.put("thread_description",thread_info.getString("description"));
                    data.put("thread_created_at",thread_info.getString("created_at"));
                    data.put("thread_updated_at", thread_info.getString("updated_at"));
                    for (int i=0;i<comments.length();i++){
                        JSONObject object = new JSONObject();
                        object.put("comment_edited",times_edited.getString(i));

                        object.put("comment_description",comments.getJSONObject(i).getString("description"));
                        object.put("comment_created",comments.getJSONObject(i).getString("created_at"));
                        object.put("comment_name",comment_user.getJSONObject(i).getString("first_name").concat(" ").concat(comment_user.getJSONObject(i).getString("last_name")));
                        object.put("comment_entry",comment_user.getJSONObject(i).getString("entry_no"));
                        object.put("comment_email",comment_user.getJSONObject(i).getString("email"));
                        comment_list.put(object);

                    }
                    successcallback();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(mycontext, "Error2" + error.getMessage(), duration);
                toast.show();
            }
        }) ;
        myQueue.add(sr1);



    }

    public void successcallback(){

        Intent intent = new Intent(mycontext,ThreadDetails.class);
        Bundle bundle = new Bundle();
        Log.i("hagga",String.valueOf(comment_list.length()));
        bundle.putString("THREAD_INFO", data.toString());
        bundle.putString("COMMENT_LIST", comment_list.toString());
        intent.putExtras(bundle);
//        Log.i("hagga","whynot");
        mycontext.startActivity(intent);

    }


}
