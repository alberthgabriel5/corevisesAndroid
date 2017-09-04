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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class RegisterGameActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName;
    private EditText etPrice;
    private Spinner spConsol;
    private Spinner spType;
    private Button btnRegister;

    private final static String[] names = { "Accion", "Aventura", "Deportes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Register Game");
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
        this.etName = (EditText)findViewById(R.id.etRegisterNameGame);
        this.etPrice = (EditText)findViewById(R.id.etRegisterPrice);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, names);
        this.spType = (Spinner)findViewById(R.id.spRegisterGameType);
        this.spType.setAdapter(adapter);
        this.spConsol = (Spinner)findViewById(R.id.spRegisterGameConsol);
        this.spConsol.setAdapter(adapter);
        this.btnRegister = (Button) findViewById(R.id.btnRegisterGame);
        this.btnRegister.setOnClickListener(this);
    }//init

    private void RegisterGame(){
            final String name = this.etName.getText().toString().trim();
            final float price = Float.parseFloat(this.etPrice.getText().toString().trim());
            final String type = this.spType.getSelectedItem().toString().trim();
            final String consol = this.spConsol.getSelectedItem().toString().trim();


            final String REGISTER_URL = "http://192.168.42.193:8094/api/Game/insert";
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("name", name);
             params.put("price", String.valueOf(price));
            params.put("type", type);
            params.put("consol", consol);

            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (response != null) {
                                Toast.makeText(RegisterGameActivity.this, "Saving...", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(RegisterGameActivity.this, "Error", Toast.LENGTH_LONG).show();

                            }//if-else
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterGameActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admi, menu);
        return true;
    }//onCreateOptionsMenu


    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnRegister.getId()){
            RegisterGame();
        }
    }
}
