package com.example.danico.mimomamemima.model.dao;

import com.example.danico.mimomamemima.herramientas.ResultListener;
import com.example.danico.mimomamemima.herramientas.ServiceRetrofit;
import com.example.danico.mimomamemima.model.pojo.paraRetrofit.ContenedorObraRetrofit;
import com.example.danico.mimomamemima.model.pojo.paraRetrofit.ObraRetrofit;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by danico on 25/07/2018.
 */

public class DaoRetrofit {

    /*** Atributos que voy a utilizar ***/
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.myjson.com";
    private ServiceRetrofit serviceRetrofit;


    /*public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }*/

    public DaoRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        serviceRetrofit = retrofit.create(ServiceRetrofit.class);
    }

    public void obtenerObraConRetrofit(final ResultListener<List<ObraRetrofit>> escuchadorDelControlador) {
        Call<ContenedorObraRetrofit> telefonoDelDaoParaPedirObras = serviceRetrofit.getTodasLasObras();
        telefonoDelDaoParaPedirObras.enqueue(new Callback<ContenedorObraRetrofit>() {
            @Override
            public void onResponse(Call<ContenedorObraRetrofit> call, Response<ContenedorObraRetrofit> response) {
                ContenedorObraRetrofit contenedorObraRetrofit = response.body();
                escuchadorDelControlador.finish(contenedorObraRetrofit.getListaObrasDesdeRetrofit());

            }

            @Override
            public void onFailure(Call<ContenedorObraRetrofit> call, Throwable t) {
                escuchadorDelControlador.finish(new ArrayList<ObraRetrofit>());
            }
        });
    }


}
