package com.example.danico.mimomamemima.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.danico.mimomamemima.model.pojo.paraRoom.ObraRoom;

import java.util.List;

/**
 * Created by danico on 26/07/2018.
 */

@Dao
public interface DaoRoom {
    @Insert
    void agregarObraRoom(ObraRoom obraRoom);

    @Query("select * from obraRoom")
    List<ObraRoom>leerObras();
}
