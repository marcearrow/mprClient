package com.example.mpr.controlers;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mpr.R;
import com.example.mpr.utils.ConexionSQLiteHelper;
import com.example.mpr.utils.Utilidades;

public class DetalleFavorito extends AppCompatActivity {

  TextView mDetail;
  ImageView mImage;
  TextView mUrl;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.cardview_detallefavorito);

    //ActionBar
    ActionBar actionBar = getSupportActionBar();
    //Título ActionBar
    actionBar.setTitle("Detalle");
    //Boton para volver atras en ActionBar
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowHomeEnabled(true);

    //Inicializar views
    mImage = findViewById(R.id.detalleArreglo_img_id);
    mDetail = findViewById(R.id.detalleArreglo_text_id);

    //Tomar datos del Intent
    byte[] bytes = getIntent().getByteArrayExtra("imgUrl");
    String description = getIntent().getStringExtra("descripcion");
    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

    //Poner datos a las views
    mImage.setImageBitmap(bmp);
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
    eliminarFavorito();
    Intent intent = new Intent(this, Favorito.class);
    startActivity(intent);
  }

  private void eliminarFavorito() {
    String[] id = {getIntent().getStringExtra("id")};
    String TAG = "eliminarListaBotton";
    Log.d(TAG, "eliminarFavorito: " + id[0]);

    ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_favoritos", null, 1);
    SQLiteDatabase db = conn.getWritableDatabase();

    db.delete(Utilidades.tablaFavoritos, Utilidades.campoImgUrl + "=?", id);
    db.close();
  }
}
