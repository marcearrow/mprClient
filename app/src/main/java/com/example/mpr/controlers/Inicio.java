package com.example.mpr.controlers;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mpr.R;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);


    }

    //Metodo para mostrar y ocultar el menu.
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        int mItm = menuItem.getItemId();

        if (mItm == R.id.itm_favoritos){
            Intent intent = new Intent(this,Favorito.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void Comenzar(View view){
        Intent intent = new Intent(view.getContext(), Evento.class);
        startActivity(intent);
    }
}
