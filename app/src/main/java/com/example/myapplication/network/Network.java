package com.example.myapplication.network;

import android.content.Context;
import android.util.Log;
import androidx.annotation.Nullable;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.utils.MyApplication;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Network {

    public static Network network;
    Context context;
//    List<NameValuePair> pairs;


    public Network(Context context) {
        this.context = context;
//        pairs = new ArrayList<>();
    }

    public static Network getInstance(Context context) {
        if (network == null)
            network = new Network(context);
        return network;
    }

    public void requestWithJsonObject(String url, HashMap<String, String> params, final VolleyResponse vr, String type) {
        Log.d("inrequestWithJsonObject", "in the requestWithJsonObject");
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("StringRequest", url);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(type.equals("")) {
                        vr.onResponse(jsonObject);
                    } else {
                        vr.onResponse2(type, jsonObject);
                    }
                } catch (Exception e) {
                    Log.d("catch_error", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.toString());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        Log.d("Network", "Sending request to: " + url);
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyApplication.getInstance().getRequestQueue().add(request);

    }
}
