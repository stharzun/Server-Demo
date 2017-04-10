package com.project.arzun.serverdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by arzun on 3/24/17.
 */

public class RegisterActivity extends AppCompatActivity {
    EditText username,password;
    Button signup;
    String registerUrl="http://silptechrent.eu5.org/registerRoutine.php";
    RequestQueue requestQueue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        username=(EditText)findViewById(R.id.suusername);
        password=(EditText)findViewById(R.id.supassword);
        signup=(Button)findViewById(R.id.susignup);

        requestQueue= Volley.newRequestQueue(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerProcess();
            }
        });
    }

    private void registerProcess() {
        final String user=username.getText().toString();
        final String pass=password.getText().toString();

        StringRequest request=new StringRequest(Request.Method.POST, registerUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject newobj=new JSONObject(response);
                    String a=String.valueOf(newobj.getInt("success"));
                    if(a.equals("1")){
                        Intent intent=new Intent(RegisterActivity.this,SigninActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Invalid credentials.", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(RegisterActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
                params.put("username",user);
                params.put("password",pass);
                return params;
            }
        };
        requestQueue.add(request);
    }
}
