package com.example.alfonso.beargamer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class BearGamerActivty extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogIn;
    private EditText etUserAdmi;
    private EditText etPassAdmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bear_gamer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("BEAR GAMER");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        init();
    }//constructor

    public void init(){

        this.btnLogIn = (Button) findViewById(R.id.btnLogIn);
        this.etUserAdmi = (EditText) findViewById(R.id.etUserAdmi);
        this.etPassAdmi = (EditText) findViewById(R.id.etPassAdmi);
        this.btnLogIn.setOnClickListener(this);

    }//init



    public void initSessionAdmi() {
        final String user = this.etUserAdmi.getText().toString().trim();
        final String password = this.etPassAdmi.getText().toString().trim();


        final String REGISTER_URL = "http://192.168.42.193:8094/api/Admi/initSession";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("user", user);
        params.put("password", password);


        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL,new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(BearGamerActivty.this,response.toString(),Toast.LENGTH_LONG);
                        if (response != null) {
                            Toast.makeText(BearGamerActivty.this, "LogIn...", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),AdmiActivity.class);
                            i.putExtra("user",user);
                            startActivity(i);
                        } else {
                            Toast.makeText(BearGamerActivty.this, "Error", Toast.LENGTH_LONG).show();

                        }//if-else

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BearGamerActivty.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {};

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnLogIn.getId()){
           initSessionAdmi();
        }//if
    }//onCñick
}//end class
