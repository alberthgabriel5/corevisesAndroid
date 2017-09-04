package com.example.alfonso.beargamerclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alfonso.beargamerclient.model.Client;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class BearGamerClientActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmailClient;
    private EditText etPassClient;
    private Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bear_gamer_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        init();

    }//contructor

    public void init(){
        this.etEmailClient = (EditText) findViewById(R.id.etEmailClient);
        this.etPassClient = (EditText) findViewById(R.id.etPassClient);
        this.btnLogIn = (Button) findViewById(R.id.btnLogInClient);
        this.btnLogIn.setOnClickListener(this);
    }//init

    private void initSessionClient() {
        final String email = this.etEmailClient.getText().toString().trim();
        final String password = this.etPassClient.getText().toString().trim();


        final String REGISTER_URL = "http://192.168.42.193:8094/api/Client/initSession";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password", password);


        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL,new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(BearGamerClientActivity.this,response.toString(),Toast.LENGTH_LONG);
                        if (response != null) {
                            Toast.makeText(BearGamerClientActivity.this, "LogIn...", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),GamesActivity.class);
                            i.putExtra("email",email);
                            startActivity(i);
                        } else {
                            Toast.makeText(BearGamerClientActivity.this, "Error", Toast.LENGTH_LONG).show();

                        }//if-else

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BearGamerClientActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {};

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bear_gamer_client, menu);
        return true;
    }//onCreateOptionsMenu

    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i;
        switch (item.getItemId()) {
            case R.id.itRegister:

                i = new Intent(getApplicationContext(),RegisterClientActivity.class);
                startActivity(i);

                return true;
            case R.id.itLogIn:
                i = new Intent(getApplicationContext(),BearGamerClientActivity.class);
                startActivity(i);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }//onOptionsItemSelected


    @Override
    public void onClick(View v) {

            if(v.getId()==this.btnLogIn.getId()) {
                initSessionClient();
            }//end if
    }//onClick
}
