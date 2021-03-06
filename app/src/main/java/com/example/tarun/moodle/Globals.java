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

    private String name;
    private String email;
    private String entry_number;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEntry_number() {
        return entry_number;
    }

    public void setEntry_number(String entry_number) {
        this.entry_number = entry_number;
    }
}