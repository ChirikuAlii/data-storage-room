package com.duniaeureka.datastoragebinar;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by chirikualii on 02/04/18.
 */

@Database(entities = {Transaksi.class} , version = 2)
public abstract class MyAppDatabase extends RoomDatabase{

    abstract TransaksiDao getTransaksiDao();
}
