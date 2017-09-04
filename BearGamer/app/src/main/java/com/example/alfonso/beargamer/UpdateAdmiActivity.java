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

public class UpdateAdmiActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etUser;
    private EditText etPass;
    private EditText etPass2;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Update Administrator");
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
        this.etUser = (EditText) findViewById(R.id.etUpdateUsernameAdmi);
        this.etPass = (EditText) findViewById(R.id.etUpdatePassAdmi);
        this.etPass2 = (EditText) findViewById(R.id.etUpdatePass2Admi);
        this.btnUpdate = (Button) findViewById(R.id.btnUpdateAdmi);
        this.btnUpdate.setOnClickListener(this);

    }//init

    private void UpdateAdmi(){
        if(this.etPass.getText().toString().equals(this.etPass2.getText().toString())) {
            final String user = this.etUser.getText().toString().trim();
            final String password = this.etPass.getText().toString().trim();


            final String REGISTER_URL = "http://192.168.42.193:8094/api/Admi/update";
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("user", user);
            params.put("password", password);


            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                                Toast.makeText(UpdateAdmiActivity.this, "Updating...", Toast.LENGTH_LONG).show();

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(UpdateAdmiActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }//onErrorResponse
                    }) {
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }else {
            Toast.makeText(UpdateAdmiActivity.this, "Password Incorrect", Toast.LENGTH_LONG).show();
        }//else-if

    }//UpdateAdmi
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admi, menu);
        return true;
    }//onCreateOptionsMenu

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnUpdate.getId()){
            UpdateAdmi();
        }//end if
    }
}//end class
