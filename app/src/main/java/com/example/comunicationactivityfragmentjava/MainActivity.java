package com.example.comunicationactivityfragmentjava;

import android.os.Bundle;

import com.example.comunicationactivityfragmentjava.controller.activity.MainActivityController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements MainFragment.OnNumeroAleatorio {

    private final MainFragment mainFrag = new MainFragment();
    FloatingActionButton fab=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MainActivityController.Companion.addFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainFrag);
        final EditText etMin = findViewById(R.id.etMin);
        final EditText etMax = findViewById(R.id.etMax);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    MainActivityController.Companion.actualizarNumeroAleatorio(mainFrag, etMin, etMax);
                }catch (Exception e){
                    Snackbar.make(view, getString(R.string.error_limites), Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void actualizado(int numero) {
        Snackbar.make(fab, getString(R.string.numero_aleatorio_actualizado)+" "+numero, Snackbar.LENGTH_LONG).show();
    }
}
