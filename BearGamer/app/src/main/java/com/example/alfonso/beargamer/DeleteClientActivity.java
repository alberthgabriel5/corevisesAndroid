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

public class DeleteClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("DeleteClient");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }//constructor

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admi, menu);
        return true;
    }//onCreateOptionsMenu

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

}//end class

