package com.example.mpr.views;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mpr.R;
import com.example.mpr.adapters.FavoritosAdapter;
import com.example.mpr.models.Producto;
import com.example.mpr.utils.ConexionSQLiteHelper;
import com.example.mpr.utils.Utilidades;
import java.util.ArrayList;

public class Favorito extends AppCompatActivity {

  ArrayList<Producto> listaFavoritos;
  RecyclerView mRecyclerView;
  ImageView imgView;

  ConexionSQLiteHelper conn;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.recyclerview);

    //ActionBar
    ActionBar actionBar = getSupportActionBar();
    //Título ActionBar
    actionBar.setTitle("Fotos Guardadas");
    //Boton para volver atras en ActionBar
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowHomeEnabled(true);

    conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_favoritos", null, 1);

    listaFavoritos = new ArrayList<>();
    mRecyclerView = findViewById(R.id.recyclerview_id);
    mRecyclerView.setHasFixedSize(true);

    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

    consultarListaFavoritos();

    FavoritosAdapter adapter = new FavoritosAdapter(listaFavoritos);
    mRecyclerView.setAdapter(adapter);


  }


  private void consultarListaFavoritos() {
    SQLiteDatabase db = conn.getReadableDatabase();
    Producto producto = null;
    Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.tablaFavoritos, null);

    while (cursor.moveToNext()) {
      producto = new Producto(null, cursor.getString(0), cursor.getString(1));

      listaFavoritos.add(producto);
    }
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }
}


