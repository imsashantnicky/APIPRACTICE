package com.example.myapplication.utils;

import android.app.Application;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.network.Network;

public class MyApplication extends Application {

    protected static MyApplication instance;
    private static RequestQueue mRequest;

    public static Network apinetwork;

    public MyApplication() {
        super();
        instance = this;
    }


    public static MyApplication get() {
        return instance;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        apinetwork = Network.getInstance(this);
        mRequest= Volley.newRequestQueue(this);
    }

    public RequestQueue getRequestQueue(){
        return mRequest;
    }

}
