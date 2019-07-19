package com.example.mpr.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.mpr.models.Etiqueta;
import com.example.mpr.utils.ImageLoader;
import com.example.mpr.controlers.ProductoActivity;
import com.example.mpr.controlers.Categoria;
import com.example.mpr.controlers.Tematica;
import java.util.ArrayList;

public class EtiquetaAdapter extends RecyclerView.Adapter<EtiquetaAdapter.viewHolderEventos> {

  private int resource;
  private ArrayList<Etiqueta> eventoLista;
  private Context context;

  public EtiquetaAdapter(int resource, ArrayList<Etiqueta> eventoLista, Context context) {
    this.resource = resource;
    this.eventoLista = eventoLista;
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
    Etiqueta evento = eventoLista.get(position);
    final String idevento = evento.getId();
    holder.textView.setText(evento.getNombre());
    if (evento.getImgUrl() != null) {

      try {
        ImageLoader imageLoader = new ImageLoader(context);
        imageLoader.setImgWithGlide(evento.getImgUrl(), holder.imageView);
      } catch (Exception ex) {
        Toast.makeText(context, "Ocurrio un error", Toast.LENGTH_SHORT).show();

      }

      holder.cardView.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent;
          switch (v.getContext().getClass().getSimpleName()) {
            case "Evento":
              intent = new Intent(v.getContext(), Tematica.class);
              intent.putExtra("id", idevento);
              v.getContext().startActivity(intent);
              break;
            case "Tematica":
              intent = new Intent(v.getContext(), Categoria.class);
              intent.putExtra("id", idevento);
              v.getContext().startActivity(intent);
              break;

            case "Categoria":
              intent = new Intent(v.getContext(), ProductoActivity.class);
              intent.putExtra("id", idevento);
              v.getContext().startActivity(intent);
              break;

          }

        }
      });

    }
  }

  @Override
  public int getItemCount() {
    return eventoLista.size();
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
