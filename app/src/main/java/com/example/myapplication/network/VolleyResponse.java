package com.example.myapplication.network;

import org.json.JSONObject;

public interface VolleyResponse {
    void onResponse(JSONObject json) throws Exception;
    void onResponse2(String url_typ, JSONObject json) throws Exception;

}
