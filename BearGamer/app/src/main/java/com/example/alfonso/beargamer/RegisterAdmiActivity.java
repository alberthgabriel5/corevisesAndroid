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

public class RegisterAdmiActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etUser;
    private EditText etPass;
    private EditText etPass2;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Register Administrator");
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

        this.etUser = (EditText)findViewById(R.id.etRegisterUserAdmi);
        this.etPass = (EditText)findViewById(R.id.etRegisterPassAdmi);
        this.etPass2 = (EditText)findViewById(R.id.etRegisterPass2Admi);
        this.btnRegister = (Button) findViewById(R.id.btnRegisterAdmi);
        this.btnRegister.setOnClickListener(this);
    }//end init

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admi, menu);
        return true;
    }//onCreateOptionsMenu

    public void RegisterAdmi(){
        if(this.etPass.getText().toString().equals(this.etPass2.getText().toString())) {
            final String user = this.etUser.getText().toString().trim();
            final String password = this.etPass.getText().toString().trim();


            final String REGISTER_URL = "http://192.168.42.193:8094/api/Admi/insert";
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("user", user);
            params.put("password", password);


            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (response != null) {
                                Toast.makeText(RegisterAdmiActivity.this, "Saving...", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(RegisterAdmiActivity.this, "Error", Toast.LENGTH_LONG).show();

                            }//if-else
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterAdmiActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }else{

            Toast.makeText(RegisterAdmiActivity.this, "Password Incorrect", Toast.LENGTH_LONG).show();

        }//end if else
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i;
        switch (item.getItemId()) {
            case R.id.imAdmiRegister:

                i = new Intent(getApplicationContext(),RegisterAdmiActivity.class);
                startActivity(i);

                return true;
            case R.id.imAdmiUpdate:
                i = new Intent(getApplicationContext(),UpdateAdmiActivity.class);
                startActivity(i);
                return true;
            case R.id.imAdmiDelete:
                i = new Intent(getApplicationContext(),RegisterAdmiActivity.class);
                startActivity(i);
                return  true;
            case R.id.imAdmiSearch:
                i = new Intent(getApplicationContext(),RegisterAdmiActivity.class);
                startActivity(i);
                return  true;
            case R.id.imClientRegister:
                i = new Intent(getApplicationContext(),RegisterClientActivity.class);
                startActivity(i);
                return  true;
            case R.id.imClientUpdate:
                i = new Intent(getApplicationContext(),UpdateClientActivity.class);
                startActivity(i);
                return  true;
            case R.id.imClientDelete:
                i = new Intent(getApplicationContext(),DeleteClientActivity.class);
                startActivity(i);
                return  true;
            case R.id.imClientSearch:
                i = new Intent(getApplicationContext(),SearchClientActivity.class);
                startActivity(i);
                return  true;
            case R.id.imGameRegister:
                i = new Intent(getApplicationContext(),RegisterGameActivity.class);
                startActivity(i);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }//onOptionsItemSelected
    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnRegister.getId()){
            RegisterAdmi();
        }//end if
    }//onClick
}//end class
