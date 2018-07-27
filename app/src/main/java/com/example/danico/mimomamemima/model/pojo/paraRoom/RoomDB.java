package com.example.danico.mimomamemima.model.pojo.paraRoom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.danico.mimomamemima.model.dao.DaoRoom;

/**
 * Created by danico on 26/07/2018.
 */

@Database(entities = {ObraRoom.class}, version = 3)
public abstract class RoomDB extends RoomDatabase {

    public abstract DaoRoom daoRoom();
}
