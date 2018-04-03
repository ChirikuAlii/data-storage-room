package com.duniaeureka.datastoragebinar; /**
 * Created by chirikualii on 02/04/18.
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

@Entity(tableName = "transaksi")
@TypeConverters(TypeAdapterPajak.class)
public class Transaksi {

    public void setNoNota(int noNota) {
        this.noNota = noNota;
    }

    @PrimaryKey(autoGenerate = true)
    private int noNota;
    private String produk;
    private int harga;

    public List<Pajak> getPajak() {
        return pajak;
    }

    public void setPajak(List<Pajak> pajak) {
        this.pajak = pajak;
    }

    private List<Pajak> pajak;

    public String getKategory() {
        return kategory;
    }

    public void setKategory(String kategory) {
        this.kategory = kategory;
    }

    private String kategory;

    public Transaksi(String produk, int harga) {

        this.produk = produk;
        this.harga = harga;
    }


    public int getNoNota() {
        return noNota;
    }

    public String getProduk() {
        return produk;
    }

    public int getHarga() {
        return harga;
    }
}



