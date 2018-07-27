package com.example.danico.mimomamemima.model.pojo.paraRetrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by danico on 26/07/2018.
 */

public class ContenedorObraRetrofit {

    @SerializedName("paints")  //aca le tengo que poner exactamente
                               // el mismo nombre que el objeto de donde saco la info con Retrofit


    /*** atributo ***/
   /* Esta lista la voy a ir formando a medida que voy guardando las
    obras instanciadas de ObraRetrofit con los datos obtenidos
     con Retrofit que vienen del Controlador*/
    private List<ObraRetrofit> listaObrasDesdeRetrofit;

    /*** Constructor ***/
    public ContenedorObraRetrofit(List<ObraRetrofit> listaObrasDesdeRetrofit) {
        this.listaObrasDesdeRetrofit = listaObrasDesdeRetrofit;
    }

  /*** Getter ***/ // asi desp puedo agarrar los elementos que necesite
    public List<ObraRetrofit> getListaObrasDesdeRetrofit() {
        return listaObrasDesdeRetrofit;
    }
}
