package com.example.mpr.controlers;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
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

public class Tematica extends AppCompatActivity {

  RecyclerView mRecyclerView;
  FirebaseDatabase mFirebaseDatabase;
  DatabaseReference mRef;
  String idEvento;
  String etiqueta="tematica";

  /**
   * Se crea el Activity Evento
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.recyclerview);

    idEvento=getIntent().getStringExtra("id");
    //ActionBar
    ActionBar actionBar = getSupportActionBar();
    //Título ActionBar
    actionBar.setTitle("Elige la Temática");

    //Boton para volver atras en ActionBar
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowHomeEnabled(true);

    mRecyclerView = findViewById(R.id.recyclerview_id);
    mRecyclerView.setHasFixedSize(true);

    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    mFirebaseDatabase = FirebaseDatabase.getInstance();
    mRef = mFirebaseDatabase.getReference("tematicas");

    ListaTematicas();

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


  private void ListaTematicas() {
    FirebaseHelper firebaseHelper = new FirebaseHelper(mRef);
    firebaseHelper.ListarConRelacion(new FirebaseCallbackListas() {
      @Override
      public void onCallback(ArrayList<Etiqueta> listaDeTematicas) {
        EtiquetaAdapter tematicaAdapter = new EtiquetaAdapter(R.layout.cardview, listaDeTematicas,
            Tematica.this);
        mRecyclerView.setAdapter(tematicaAdapter);
      }
    }, idEvento,etiqueta);
  }


  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

}
