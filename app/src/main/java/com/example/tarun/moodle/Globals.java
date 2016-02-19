package com.example.tarun.moodle;

import android.app.Application;

/**
 * Created by hd on 19/2/16.
 */


//set the server address in the login activity
public class Globals extends Application {

    private String serverAddress;

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}