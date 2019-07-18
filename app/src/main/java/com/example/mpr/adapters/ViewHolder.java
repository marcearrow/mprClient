package com.example.mpr.adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mpr.R;
import com.example.mpr.views.Tematica;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;
    String eventoID;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;

        //click item
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(v.getContext(), Tematica.class);
              intent.putExtra("idEvento",eventoID);
            }
        });
        //long click item
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v,getAdapterPosition());
                return true;
            }
        });
    }

    public  void setDetails(Context ctx,String id,String description, String image,String idEvento) {
        //Views
        ImageView mImage = mView.findViewById(R.id.cardview_img_id);
        TextView mDescription = mView.findViewById(R.id.cardview_text_id);
        TextView txtId = mView.findViewById(R.id.cardview_textId_id);

        //Set data to views
        txtId.setText(image);
        mDescription.setText(description);
        Picasso.get().load(image).into(mImage);
        eventoID=idEvento;


    }


    private  ViewHolder.ClickListener mClickListener;

    //
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;

    }

}
