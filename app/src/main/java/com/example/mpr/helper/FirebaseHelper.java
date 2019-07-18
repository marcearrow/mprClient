package com.example.mpr.helper;

import androidx.annotation.NonNull;
import com.example.mpr.models.Etiqueta;
import com.example.mpr.models.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class FirebaseHelper {

  private DatabaseReference db;
  private DatabaseReference db2;
  private Boolean estado;

  //constructor firebasehelper
  public FirebaseHelper(DatabaseReference db) {
    this.db = db;
  }

  public void ListaEventos(final FirebaseCallbackListas firebaseCallbackListas) {
    final ArrayList<Etiqueta> eventos = new ArrayList<>();
    db.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        eventos.clear();
        for (DataSnapshot ds :
            dataSnapshot.getChildren()) {
          String nombre = ds.child("nombre").getValue().toString();
          String id = ds.child("id").getValue().toString();
          String imgUrl = ds.child("imgUrl").getValue().toString();
          eventos.add(new Etiqueta(id, nombre, imgUrl));
        }
        firebaseCallbackListas.onCallback(eventos);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  public void ListarConRelacion(final FirebaseCallbackListas firebaseCallbackListas, String key,
      String etiqueta) {

    final String childId;
    switch (etiqueta) {
      case "tematica":
        childId = "evento";
        break;
      case "categoria":
        childId = "tematica";
        break;

      default:
        childId = "id";
    }

    final ArrayList<Etiqueta> itemList = new ArrayList<>();
    db.orderByChild(childId).equalTo(key).addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        itemList.clear();
        for (DataSnapshot ds :
            dataSnapshot.getChildren()) {
          String nombre = ds.child("nombre").getValue().toString();
          String id = ds.child("id").getValue().toString();
          String imgUrl = ds.child("imgUrl").getValue().toString();
          itemList.add(new Etiqueta(id, nombre, imgUrl));
        }

        firebaseCallbackListas.onCallback(itemList);


      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  public void ListarProductos(final FirebaseCallbackProductos firebaseCallbackProductos, String key) {

    final ArrayList<Producto> itemList = new ArrayList<>();

    db.orderByChild("categoria").equalTo(key).addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        itemList.clear();
        for (DataSnapshot ds :
            dataSnapshot.getChildren()) {
          String nombre = ds.child("nombre").getValue().toString();
          String id = ds.child("id").getValue().toString();
          String imgUrl = ds.child("imgUrl").getValue().toString();
          itemList.add(new Producto(id,imgUrl,nombre));
        }

        firebaseCallbackProductos.onCallbacKProducto(itemList);


      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  public interface FirebaseCallbackListas {

    void onCallback(ArrayList<Etiqueta> listaDeEventos);

  }

  public interface FirebaseCallbackProductos {

    void onCallbacKProducto(ArrayList<Producto> listaDeProductos);
  }

}

