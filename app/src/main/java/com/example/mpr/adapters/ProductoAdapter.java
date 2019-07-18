package com.example.mpr.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mpr.R;
import com.example.mpr.models.Producto;
import com.example.mpr.utils.ImageLoader;
import com.example.mpr.views.ProductoDetalle;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.viewHolderEventos> {

  private int resource;
  private ArrayList<Producto> productoLista;
  private Context context;

  public ProductoAdapter(int resource, ArrayList<Producto> productoLista, Context context) {
    this.resource = resource;
    this.productoLista = productoLista;
    this.context = context;
  }


  @NonNull
  @Override
  public viewHolderEventos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
    return new viewHolderEventos(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final viewHolderEventos holder, int position) {
    final Producto producto = productoLista.get(position);
    final String idProducto = producto.getId();
    final String nombreProducto= producto.getNombre();

    holder.textView.setText(nombreProducto);
    if (producto.getImgUrl() != null) {

      try {
        ImageLoader imageLoader = new ImageLoader(context);
        imageLoader.setImgWithGlide(producto.getImgUrl(), holder.imageView);
      } catch (Exception ex) {
        Toast.makeText(context, "Ocurrio un error", Toast.LENGTH_SHORT).show();

      }

      holder.cardView.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {

          Intent intent = new Intent(v.getContext(), ProductoDetalle.class);
          Drawable mDrawable = holder.imageView.getDrawable();
          if (mDrawable != null) {
            Bitmap bitmap = ((BitmapDrawable) mDrawable).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bytes = stream.toByteArray();
            intent.putExtra("imgUrl", bytes);//Poner imagen bitmap como arreglo de bytes
          }

          intent.putExtra("descripcion", nombreProducto);
          intent.putExtra("url", producto.getImgUrl());

          v.getContext().startActivity(intent);


        }
      });

    }
  }

  @Override
  public int getItemCount() {
    return productoLista.size();
  }

  class viewHolderEventos extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textView;
    CardView cardView;

    private viewHolderEventos(View itemView) {
      super(itemView);

      this.imageView = itemView.findViewById(R.id.cardview_img_id);
      this.textView = itemView.findViewById(R.id.cardview_text_id);
      this.cardView = itemView.findViewById(R.id.cardview_id);

    }
  }

}
