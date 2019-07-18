package com.example.mpr.views;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mpr.R;
import com.example.mpr.adapters.EtiquetaAdapter;
import com.example.mpr.helper.FirebaseHelper;
import com.example.mpr.helper.FirebaseHelper.FirebaseCallbackListas;
import com.example.mpr.models.Etiqueta;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class Categoria extends AppCompatActivity {

  RecyclerView mRecyclerView;
  FirebaseDatabase mFirebaseDatabase;
  DatabaseReference mRef;
  String idTematica;
  String etiqueta="categoria";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.recyclerview);

    idTematica=getIntent().getStringExtra("id");

    ActionBar actionBar = getSupportActionBar();
    //Título ActionBar
    actionBar.setTitle("Elige la Categoría");

    //Boton para volver atras en ActionBar
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowHomeEnabled(true);

    mRecyclerView = findViewById(R.id.recyclerview_id);
    mRecyclerView.setHasFixedSize(true);

    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    mFirebaseDatabase = FirebaseDatabase.getInstance();
    mRef = mFirebaseDatabase.getReference("categorias");
    ListaCategorias();
  }

  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_overflow, menu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem menuItem) {
    int mItm = menuItem.getItemId();

    if (mItm == R.id.itm_favoritos) {
      Intent intent = new Intent(this, Favorito.class);
      startActivity(intent);
    }
    return super.onOptionsItemSelected(menuItem);
  }

  private void ListaCategorias() {
    FirebaseHelper firebaseHelper = new FirebaseHelper(mRef);
    firebaseHelper.ListarConRelacion(new FirebaseCallbackListas() {
      @Override
      public void onCallback(ArrayList<Etiqueta> listaDeCategorias) {
        EtiquetaAdapter categoriaAdapter = new EtiquetaAdapter(R.layout.cardview, listaDeCategorias,
            Categoria.this);
        mRecyclerView.setAdapter(categoriaAdapter);
      }
    }, idTematica,etiqueta);
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

}
