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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by arzun on 3/24/17.
 */
public class SigninActivity extends AppCompatActivity{
    EditText username,password;
    Button signin,signup;
    RequestQueue requestQueue;
    String url="http://silptechrent.eu5.org/loginRoutine.php";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        username=(EditText)findViewById(R.id.siusername);
        password=(EditText)findViewById(R.id.sipassword);
        signin=(Button)findViewById(R.id.sisignin);
        signup=(Button)findViewById(R.id.sisignup);

        requestQueue= Volley.newRequestQueue(this);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinProcess();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SigninActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void signinProcess() {
        final String user=username.getText().toString();
        final String pass=password.getText().toString();
        final StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject newobj=new JSONObject(response);
                    String session=String.valueOf(newobj.getInt("success"));
                   // String message=String.valueOf(newobj.getString("message"));
                    if(session.equals("1")){
                        Intent intent=new Intent(SigninActivity.this,MainActivity.class);
                        startActivity(intent);
                    //    Toast.makeText(SigninActivity.this, message, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SigninActivity.this, "TRy again", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(SigninActivity.this, "couldnot fetch data", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SigninActivity.this, "no internet connection", Toast.LENGTH_SHORT).show();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String>mydata=new HashMap<>();
                mydata.put("username",user);
                mydata.put("password",pass);
                return mydata;
            }
        };
        requestQueue.add(request);
    }
}
