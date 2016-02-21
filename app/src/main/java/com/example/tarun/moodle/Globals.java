package com.example.tarun.moodle;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by hd on 19/2/16.
 */


//set the server address in the login activity
public class Globals extends Application {

    private String serverAddress;
    private RequestQueue volleyQueue;

    public String getServerAddress() {
        return serverAddress;
    }


    public RequestQueue getVolleyQueue(){
        if(volleyQueue == null){
            volleyQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return volleyQueue;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}