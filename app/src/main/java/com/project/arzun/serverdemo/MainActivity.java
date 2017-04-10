package com.project.arzun.serverdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView display;
    String url="http://www.silptevhrent.eu5.org/commentsRoutine.php";
    RequestQueue requestQueue;
    ArrayList<Module> savedData=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display=(ListView)findViewById(R.id.listview);
        requestQueue= Volley.newRequestQueue(this);


        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject myobject=new JSONObject(response);
                    JSONArray postarray=myobject.getJSONArray("posts");
                    for (int i=0;i<postarray.length();i++){
                        JSONObject data=postarray.getJSONObject(i);
                        Module m=new Module();
                        m.setName(data.getString("name"));
                        m.setAddress(data.getString("address"));
                        m.setPhone(data.getString("phone"));
                        m.setCost(data.getString("cost"));
                        m.setDescription(data.getString("description"));
                        savedData.add(m);
                }
                displayData();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }

    private void displayData() {
        display.setAdapter(new MyAdapter(MainActivity.this,savedData));
    }
}
