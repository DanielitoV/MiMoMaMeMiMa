package com.example.danico.mimomamemima.controller;


import android.arch.persistence.room.Room;

import com.example.danico.mimomamemima.model.pojo.paraRoom.ObraRoom;
import com.example.danico.mimomamemima.model.pojo.paraRoom.RoomDB;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by danico on 26/07/2018.
 */

public class ControllerRoom {

    public void agregarObraRoom(ObraRoom obraRoom) {
        RoomDB roomDB = Room.databaseBuilder(getApplicationContext(), RoomDB.class, "obrasDeArteDB").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        roomDB.daoRoom().agregarObraRoom(obraRoom);
    }

    public List<ObraRoom> leerObras() {
        RoomDB roomDB = Room.databaseBuilder(getApplicationContext(), RoomDB.class, "obrasDeArteDB").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        List<ObraRoom> lista = roomDB.daoRoom().leerObras();
        return lista;
    }
}
