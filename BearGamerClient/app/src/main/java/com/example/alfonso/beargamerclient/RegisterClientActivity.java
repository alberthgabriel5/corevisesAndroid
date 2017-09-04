package com.example.alfonso.beargamerclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.alfonso.beargamerclient.model.Game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterClientActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etBirthday;
    private EditText etPass;
    private EditText etPass2;
    private EditText etName;
    ArrayList<Game> games;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Register");
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bear_gamer_client, menu);
        return true;
    }//onCreateOptionsMenu

    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i;
        switch (item.getItemId()) {
            case R.id.itRegister:

                i = new Intent(getApplicationContext(), RegisterClientActivity.class);
                startActivity(i);

                return true;
            case R.id.itLogIn:
                i = new Intent(getApplicationContext(), BearGamerClientActivity.class);
                startActivity(i);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }//onOptionsItemSelected

    public void init() {
        this.etEmail = (EditText) findViewById(R.id.etRegisterEmail);
        this.etName = (EditText) findViewById(R.id.etRegisterName);
        this.etBirthday = (EditText) findViewById(R.id.etRegisterBirthday);
        this.etPass = (EditText) findViewById(R.id.etRegisterPassClient);
        this.etPass2 = (EditText) findViewById(R.id.etRegisterClientPass2);
        this.btnRegister = (Button) findViewById(R.id.btnRegisterClient);
        this.btnRegister.setOnClickListener(this);
    }//init

    private void RegisterClient() {

        if (this.etPass.getText().toString().equals(this.etPass2.getText().toString())) {
            final String email = this.etEmail.getText().toString().trim();
            final String password = this.etPass.getText().toString().trim();
            final String name = this.etName.getText().toString().trim();
            final String birthday = this.etBirthday.getText().toString().trim();


            final String REGISTER_URL = "http://192.168.42.193:8094/api/Client/insert";
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("name", name);
            params.put("email", email);
            params.put("birthday", birthday);
            params.put("password", password);

            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if(response!=null) {
                                Toast.makeText(RegisterClientActivity.this, "Saving...", Toast.LENGTH_LONG).show();
                                etBirthday.setText("");
                                etPass2.setText("");
                                etEmail.setText("");
                                etPass.setText("");
                                etName.setText("");
                            }else{
                                Toast.makeText(RegisterClientActivity.this, "Error..", Toast.LENGTH_LONG).show();
                            }
                            }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                           Toast.makeText(RegisterClientActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }else{

            Toast.makeText(RegisterClientActivity.this, "Password Incorrect", Toast.LENGTH_LONG).show();
        }
    }//RegisterClient


    @Override
    public void onClick (View v){
        if (v.getId() == this.btnRegister.getId()) {
            RegisterClient();
        }//
    }//onClick
}
