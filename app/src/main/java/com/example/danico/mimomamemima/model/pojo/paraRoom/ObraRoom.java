package com.example.danico.mimomamemima.model.pojo.paraRoom;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by danico on 26/07/2018.
 */


@Entity(tableName = "obraDeArteRoom")
public class ObraRoom {


    @PrimaryKey(autoGenerate = true) @NonNull
    private Integer idObra;


    private Integer artistId;

    private String image, name;


    @NonNull
    public Integer getIdObra() {
        return idObra;
    }

    public void setIdObra(@NonNull Integer idObra) {
        this.idObra = idObra;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

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
}


