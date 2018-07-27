package com.example.danico.mimomamemima.model.pojo.paraRetrofit;

/**
 * Created by danico on 25/07/2018.
 */

public class ObraRetrofit {

    /*** Atributos de la Plantilla para el Modelo***/
    private String image;
    private String name;
    private Integer artistId;

    /*** Constructor ***/
    public ObraRetrofit(String image, String name, Integer artistId) {
        this.image = image;
        this.name = name;
        this.artistId = artistId;
    }


    /*** Getters y Setters**/  //Este modelito es para formar
                               // las instancias tomando la info con Retrofit
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }
}
