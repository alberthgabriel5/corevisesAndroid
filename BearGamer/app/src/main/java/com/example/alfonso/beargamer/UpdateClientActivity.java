package com.example.alfonso.beargamer;

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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class UpdateClientActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPass;
    private EditText etPass2;
    private EditText etName;
    private EditText etBirthday;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Update Client");
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
        this.etEmail = (EditText)findViewById(R.id.etUpdateEmailClient);
        this.etName = (EditText)findViewById(R.id.etUpdateEmailClient);
        this.etPass = (EditText)findViewById(R.id.etUpdateEmailClient);
        this.etBirthday = (EditText)findViewById(R.id.etUpdateClientBirthday);
        this.etPass2 = (EditText)findViewById(R.id.etUpdateClientPass2);
        this.btnUpdate = (Button)findViewById(R.id.btnUpdateClient);
        this.btnUpdate.setOnClickListener(this);
    }//init

    private void UpdateClient(){
        if(this.etPass2.getText().toString()==this.etPass.getText().toString()) {

            final String email = this.etEmail.getText().toString().trim();
            final String password = this.etPass.getText().toString().trim();
            final String name = this.etName.getText().toString().trim();
            final String birthday = this.etBirthday.getText().toString().trim();


            final String REGISTER_URL = "http://192.168.42.193:8094/api/Client/update";
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("email", email);
            params.put("password", password);
            params.put("name", name);
            params.put("birthday", birthday);


            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (response != null) {
                                Toast.makeText(UpdateClientActivity.this, "Updating...", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(UpdateClientActivity.this, "Error", Toast.LENGTH_LONG).show();

                            }//if-else
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(UpdateClientActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }//onErrorResponse
                    }) {
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(UpdateClientActivity.this, "Password Incorrect", Toast.LENGTH_LONG).show();

        }
    }//UpdateClient




    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admi, menu);
        return true;
    }//onCreateOptionsMenu

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.imAdmiRegister:

                Intent i = new Intent(getApplicationContext(), RegisterAdmiActivity.class);
                startActivity(i);

                return true;
            case R.id.imAdmiUpdate:

                return true;
            case R.id.imAdmiDelete:
                return true;
            case R.id.imAdmiSearch:
                return true;
            case R.id.imClientRegister:
                return true;
            case R.id.imClientUpdate:
                return true;
            case R.id.imClientDelete:
                return true;
            case R.id.imClientSearch:
                return true;
            case R.id.imGameRegister:
                return true;
            case R.id.imGameUpdate:
                return true;
            case R.id.imGameDelete:
                return true;
            case R.id.imGameSearch:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }//end switch
    }//onOptionsItemSelected

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnUpdate.getId()){
            UpdateClient();
        }//end if
    }
}//end class
