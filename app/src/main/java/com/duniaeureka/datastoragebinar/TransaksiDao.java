package com.duniaeureka.datastoragebinar;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by chirikualii on 02/04/18.
 */

@Dao
public interface TransaksiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void simpanTransaksi(Transaksi transaksi);

    @Query("SELECt * FROM transaksi")
    List<Transaksi> getTransactions();

    @Query("SELECT * FROM transaksi WHERE noNota = :noNota")
    List<Transaksi> getTransactionsById(int noNota);

    @Query("SELECT * FROM transaksi")
    LiveData<List <Transaksi>> getTransactionsLive();

    @Update
    void ubahTransaksi(Transaksi transaksi);
}
