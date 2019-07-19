package com.example.mpr.controlers;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mpr.R;
import com.example.mpr.utils.ConexionSQLiteHelper;
import com.example.mpr.utils.Utilidades;

public class ProductoDetalle extends AppCompatActivity {

  TextView mDetail;
  ImageView mImage;
  TextView mUrl;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.cardview_detallearreglo);

    //ActionBar
    ActionBar actionBar = getSupportActionBar();
    //Título ActionBar
    actionBar.setTitle("Detalles");
    //Boton para volver atras en ActionBar
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowHomeEnabled(true);

    //Inicializar vistas
    mImage = findViewById(R.id.detalleArreglo_img_id);
    mDetail = findViewById(R.id.detalleArreglo_text_id);

    //Tomar datos del Intent
    if (getIntent().getByteArrayExtra("imgUrl") != null) {
      byte[] bytes = getIntent().getByteArrayExtra("imgUrl");
      Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
      mImage.setImageBitmap(bmp);
    }

    String description = getIntent().getStringExtra("descripcion");

    //Poner datos a las vistas

    mDetail.setText(description);


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
  //

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

  public void onClick(View view) {
    registrarFavorito();

  }

  private void registrarFavorito() {
    //Inicializar vistas
    mDetail = findViewById(R.id.detalleArreglo_text_id);
    mUrl = findViewById(R.id.detalleArreglo_textId_id);

    ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_favoritos", null, 1);

    SQLiteDatabase db = conn.getWritableDatabase();

    ContentValues values = new ContentValues();

    //Tomar datos del Intent
    values.put(Utilidades.campoImgUrl, getIntent().getStringExtra("url"));
    values.put(Utilidades.campoDescripcion, getIntent().getStringExtra("descripcion"));

    Long imgUrl = db.insert(Utilidades.tablaFavoritos, Utilidades.campoImgUrl, values);

    Toast.makeText(getApplicationContext(), "Foto Guardada", Toast.LENGTH_SHORT).show();
    db.close();

  }

}
