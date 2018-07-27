package com.example.danico.mimomamemima.herramientas;

import com.example.danico.mimomamemima.model.pojo.paraRetrofit.ContenedorObraRetrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by danico on 25/07/2018.
 */

public interface ServiceRetrofit {

    @GET("/bins/x858r")
    Call<ContenedorObraRetrofit> getTodasLasObras();
}
