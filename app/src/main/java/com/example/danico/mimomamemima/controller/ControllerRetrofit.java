package com.example.danico.mimomamemima.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.danico.mimomamemima.herramientas.ResultListener;
import com.example.danico.mimomamemima.model.dao.DaoRetrofit;
import com.example.danico.mimomamemima.model.pojo.paraRetrofit.ObraRetrofit;
import com.example.danico.mimomamemima.model.pojo.paraRoom.ObraRoom;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by danico on 26/07/2018.
 */

public class ControllerRetrofit {

    private List<ObraRoom> listaObraDeRoom;

    private ControllerRoom controllerRoom;

    private Map<String, String> dicionarioDeArtista = new HashMap<>();
    private Bitmap bitmapImagen;




    //Este es el metodo al que le paso el escuchador de la vista para pedir los datos
    public void obtenerObrasDeRetrofit(final ResultListener<List<ObraRetrofit>> escuchadorDeLaVista) {

        controllerRoom = new ControllerRoom();
        listaObraDeRoom = controllerRoom.leerObras();



        // entonces el controlador le hace el pedido al DAO pasandole un escuchador propio, y le pide los datos
        ResultListener<List<ObraRetrofit>> escuchadorDelControlador = new ResultListener<List<ObraRetrofit>>() {
            @Override
            public void finish(List<ObraRetrofit> resultado) {
                if (!resultado.isEmpty()) {
                    escuchadorDeLaVista.finish(resultado);
                    cargoListaEnBaseDeDato(resultado);
                } else {
                    List<ObraRetrofit> listaGuardadaEnBaseDeDato = generarListaUsandoBaseDeDato(listaObraDeRoom);
                    escuchadorDeLaVista.finish(listaGuardadaEnBaseDeDato);
                }
            }
        };
        DaoRetrofit daoRetrofit = new DaoRetrofit();
        daoRetrofit.obtenerObraConRetrofit(escuchadorDelControlador);

    }

            private void cargoListaEnBaseDeDato(List<ObraRetrofit> resultado) {
        if(listaObraDeRoom.isEmpty()){
            for (ObraRetrofit obraRetrofit : resultado) {

                Integer idPintura = obraRetrofit.getArtistId();
                String nombre = obraRetrofit.getName();
                String url = obraRetrofit.getImage();


                //voy instanciando de a una obra en Room y le seteo los datos que me da retrofit
                ObraRoom obraRoom = new ObraRoom();
                obraRoom.setArtistId(idPintura);
                obraRoom.setName(nombre);
                obraRoom.setImage(url);

                controllerRoom.agregarObraRoom(obraRoom);
            }
        }
    }


    private List<ObraRetrofit> generarListaUsandoBaseDeDato(List<ObraRoom> listaObraDeRoom) {
        List<ObraRetrofit> listaParaMaterEnRoom = new ArrayList<>();

        for ( ObraRoom obraRoom : listaObraDeRoom ) {
            ObraRetrofit obraRetrofit = new ObraRetrofit(obraRoom.getImage(), obraRoom.getName(), obraRoom.getArtistId());
            listaParaMaterEnRoom.add(obraRetrofit);
        }
        return listaParaMaterEnRoom;
    }

    public void tomarDatosDeBaseDeDatos(String idArtista, final ResultListener<Map<String, String>> escuchadorDeLaVista){

        DatabaseReference artistaDB = FirebaseDatabase.getInstance().getReference().child("artist").child(idArtista.toString());

        artistaDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    dicionarioDeArtista.put("nombre", dataSnapshot.child("name").getValue(String.class));
                    dicionarioDeArtista.put("influencia", dataSnapshot.child("influencia").getValue(String.class));
                    dicionarioDeArtista.put("nacionalidad", dataSnapshot.child("nacionalidad").getValue(String.class));
                    escuchadorDeLaVista.finish(dicionarioDeArtista);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getImagenStorage (String url, final Context context, final ResultListener<Bitmap> escuchadorDeLaVista){

        String[] chilImagen = url.split("/");
        String child;
        if(chilImagen[1].isEmpty()){
            child = chilImagen[2];
        }else {
            child = chilImagen[1];
        }

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        StorageReference imageReference = storageReference.child("artistpaints").child(child);


        File localFile = null;
        try {
            localFile = File.createTempFile("images", "jpg");
            final File finalLocalFile = localFile;
            imageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>(){
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    bitmapImagen = BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                    escuchadorDeLaVista.finish(bitmapImagen);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Lo Sentimos No se Pudo Cargar La Imagen", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

