package com.example.ruchira.salesfautomation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ruchira.salesfautomation.Activity.MainMenu;
import com.example.ruchira.salesfautomation.Activity.Register;
import com.example.ruchira.salesfautomation.Connection.MySingleton;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    final String Tag=this.getClass().getName();

    EditText username;
    EditText password;
    Button login;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.button);
        register = (Button) findViewById(R.id.loginRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent(LoginActivity.this,Register.class);
                startActivity(r);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://htruchira.000webhostapp.com/login.php";
                StringRequest stringRequest1=new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        Log.d(Tag,response);
                        if (response.equals("success")){
                            Intent r2 = new Intent(LoginActivity.this,MainNavigation.class);
                            startActivity(r2);

                        }else{
                            Toast.makeText(getApplicationContext(), "Incorrect username password", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                Toast.makeText(getApplicationContext(),"Connection Fail", Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params=new HashMap<String, String>();
                        params.put("username",username.getText().toString());
                        params.put("password",password.getText().toString());
                        return params;
                    }
                };



                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest1);


            }
        });

    }




}
