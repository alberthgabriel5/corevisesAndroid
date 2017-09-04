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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SearchClientActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnSearchClient;
    private EditText etEmailUpadate;
    private TextView tvName;
    private TextView tvPass;
    private TextView tvEmail;
    private TextView tvBirtday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Search Client");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }//constructor

    public void init(){
        this.etEmailUpadate = (EditText)findViewById(R.id.etEmailForSearch);
        this.tvBirtday = (TextView)findViewById(R.id.tVBirthdayClient);
        this.tvName = (TextView)findViewById(R.id.tVNameClient);
        this.tvPass = (TextView)findViewById(R.id.tVPasswordClient);
        this.tvEmail = (TextView)findViewById(R.id.tVEmailClient);
        this.btnSearchClient = (Button)findViewById(R.id.btnSearchClient);
        this.btnSearchClient.setOnClickListener(this);
    }//init


    private void UpdateClient(){

            final String email = this.etEmailUpadate.getText().toString().trim();


            final String REGISTER_URL = "http://192.168.42.193:8094/api/Client/initSession";
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("email", email);


            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                tvName.setText(response.getString("name"));
                                tvEmail.setText(response.getString("email"));
                                tvBirtday.setText(response.getString("birhday"));
                                tvPass.setText(response.getString("password"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SearchClientActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }//onErrorResponse
                    }) {
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
    }//UpdateClient


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admi, menu);
        return true;
    }//onCreateOptionsMenu
    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnSearchClient.getId()){
            UpdateClient();
        }//if
    }
}//end class
