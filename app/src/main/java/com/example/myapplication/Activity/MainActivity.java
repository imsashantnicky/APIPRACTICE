package com.example.myapplication.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.network.Network;
import com.example.myapplication.network.VolleyResponse;
import com.example.myapplication.utils.Constants;
import org.json.JSONObject;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText tableType, updateName, updateEmail;
    TextView details;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setUserDetails();

    }

    public void init(){
        tableType = (EditText) findViewById(R.id.tableTypeET);
        updateName = (EditText) findViewById(R.id.updateNameET);
        updateEmail = (EditText) findViewById(R.id.updateEmailET);
        details = (TextView) findViewById(R.id.detailsTV);
        submitBtn = (Button) findViewById(R.id.submitBtn);
    }

    /*
    public void processdata(final String name, final String email){

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                details.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("Error", error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", name);
                map.put("email", email);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }
    */

    VolleyResponse vr = new VolleyResponse() {
        @Override
        public void onResponse(JSONObject json) throws Exception {
            details.setText(json.toString());
            Toast.makeText(getApplicationContext(), "Data from onResponse", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onResponse2(String url_typ, JSONObject json) throws Exception {
            if (url_typ.equals("student")) {
                details.setText(json.toString());
                Toast.makeText(getApplicationContext(), "Student Data Update", Toast.LENGTH_LONG).show();
            } else if (url_typ.equals("teacher")) {
                details.setText(json.toString());
                Toast.makeText(getApplicationContext(), "Teacher Data Update", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Error Getting Data", Toast.LENGTH_SHORT).show();
            }
        }
    };


    private void setUserDetails(){
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String table = tableType.getText().toString();
                String name = updateName.getText().toString();
                String email = updateEmail.getText().toString();
                HashMap<String, String> params = new HashMap<>();
                params.put("type", table);
                params.put("name", name);
                params.put("email", email);
                Network network = new Network(getApplicationContext());
                network.requestWithJsonObject(Constants.MAIN_URL, params, vr, table);
            }
        });
    }

}