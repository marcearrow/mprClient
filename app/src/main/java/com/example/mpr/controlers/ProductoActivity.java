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
import com.example.mpr.adapters.ProductoAdapter;
import com.example.mpr.helper.FirebaseHelper;
import com.example.mpr.helper.FirebaseHelper.FirebaseCallbackProductos;
import com.example.mpr.models.Producto;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class ProductoActivity extends AppCompatActivity {

  RecyclerView mRecyclerView;
  FirebaseDatabase mFirebaseDatabase;
  DatabaseReference mRef;
  String idCategoria;
  String etiqueta = "producto";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.recyclerview);

    idCategoria = getIntent().getStringExtra("id");
    //ActionBar
    ActionBar actionBar = getSupportActionBar();
    //Título ActionBar
    actionBar.setTitle("Elige las Decoraciones");
    //Boton para volver atras en ActionBar
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowHomeEnabled(true);

    mRecyclerView = findViewById(R.id.recyclerview_id);
    mRecyclerView.setHasFixedSize(true);

    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

    mFirebaseDatabase = FirebaseDatabase.getInstance();
    mRef = mFirebaseDatabase.getReference("productos");

    ListarProductos();


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
  //Cargar datos al recyclerview onStart


  private void ListarProductos() {
    FirebaseHelper firebaseHelper = new FirebaseHelper(mRef);
    firebaseHelper.ListarProductos(new FirebaseCallbackProductos() {
      @Override
      public void onCallbacKProducto(ArrayList<Producto> listaDeProductos) {
        ProductoAdapter productoAdapter = new ProductoAdapter(R.layout.cardview_arreglo,
            listaDeProductos,
            ProductoActivity.this);

        mRecyclerView.setAdapter(productoAdapter);
      }
    }, idCategoria);
  }


  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }
}
