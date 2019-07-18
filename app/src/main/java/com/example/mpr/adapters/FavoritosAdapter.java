package com.example.mpr.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mpr.R;
import com.example.mpr.models.Producto;
import com.example.mpr.views.DetalleFavorito;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.ProductosViewHolder> {

    Context mContext;
    ArrayList<Producto> listaProductos;
    String id;

    public FavoritosAdapter(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorito,null,false);

        return new ProductosViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder productosViewHolder, int i) {

        id=listaProductos.get(i).getImgUrl();
        Picasso.get().load(listaProductos.get(i).getImgUrl()).into(productosViewHolder.ImgUrl);
        productosViewHolder.descripcion.setText(listaProductos.get(i).getNombre());
        productosViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext=v.getContext();
                //Vistas
                ImageView mImage = v.findViewById(R.id.favorito_img_id);
                TextView mDesc = v.findViewById(R.id.favorito_text_id);


                //Tomar datos de las views
                String mDescription = mDesc.getText().toString();
                Drawable mDrawable = mImage.getDrawable();
                Bitmap bitmap = ((BitmapDrawable)mDrawable).getBitmap();

                //Pasar los datos al nuevo Activity
                Intent intent = new Intent(v.getContext(), DetalleFavorito.class);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] bytes = stream.toByteArray();
                intent.putExtra("imgUrl",bytes);//Poner imagen bitmap como arreglo de bytes
                intent.putExtra("descripcion",mDescription);//Poner descripcion
                intent.putExtra("id", id);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }


    public class ProductosViewHolder extends RecyclerView.ViewHolder{
        ImageView ImgUrl;
        TextView descripcion;
        CardView cardView;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgUrl=(ImageView) itemView.findViewById(R.id.favorito_img_id);
            descripcion=(TextView) itemView.findViewById(R.id.favorito_text_id);
            cardView=(CardView) itemView.findViewById(R.id.favorito_id);
        }
    }
}
