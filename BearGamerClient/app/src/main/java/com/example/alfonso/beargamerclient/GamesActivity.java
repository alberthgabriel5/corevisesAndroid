package com.example.alfonso.beargamerclient;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.alfonso.beargamerclient.model.Client;
import com.example.alfonso.beargamerclient.model.Game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GamesActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<Game> games;
    private Button btnP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
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
    }//onCreate

    public void init(){
        this.btnP = (Button)findViewById(R.id.btnPrueba);
        this.btnP.setOnClickListener(this);
    }//init

    private void allGames() {

        final String REGISTER_URL = "http://192.168.42.193:8094/api/Game/GetGames";

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, REGISTER_URL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG);



                            //for(int i =0;i<response.length(); i++){
                             //   JSONObject productObject = jsonArray.getJSONObject(i);
                               // games.add(new Game(
                                 //       productObject.getString("id"),
                                   //     productObject.getString("name"),
                                     //   productObject.getString("type"),
                                       // productObject.getString("consol")
                                //,productObject.getInt("price")));
                            //}
                          //  String names = "";
                            //for(int i =0;i<games.size(); i++){
                              //  names+=games.get(i).getName()+";";
                           // }
                            //Toast.makeText(getApplicationContext(),names,Toast.LENGTH_LONG);

                                //  ListView listView = (ListView)findViewById( R.id.lvGames );
                            //final ArrayList<Game> listItems = new ArrayList<Game>();
                            //final ArrayAdapter<Game> adapter = new ArrayAdapter<Game>( this, android.R.layout.simple_list_item_1,games,listItems);
                            //listView.setAdapter( adapter );


                    }//onRespon
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GamesActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnP.getId()){
            allGames();
        }
    }
}
