package com.duniaeureka.datastoragebinar;

import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class MainActivity extends AppCompatActivity {

    EditText edtProduk;
    private EditText edtHarga;
    private TextView txtResult;
    private MyAppDatabase build;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtProduk = findViewById(R.id.edt_produk);
        edtHarga = findViewById(R.id.edt_harga);
        txtResult = findViewById(R.id.textView);


        build = Room.databaseBuilder(getApplicationContext(),
                MyAppDatabase.class,
                "my_app.db")
                .addMigrations(Migrations.MIGRATION_1_2)
                .build();


        findViewById(R.id.btn_simpan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanTransaksi();
            }
        });
        //SharedPref sp = new SharedPref(this);
        //sp.saveBoolean(true, SharedPref.KYE_IS_LOGIN);


        build.getTransaksiDao().getTransactionsLive().observe(this, new Observer<List<Transaksi>>() {
            @Override
            public void onChanged(@Nullable List<Transaksi> transaksis) {
                String data ="";

                //get data
                for (int i =0; i < transaksis.size() ;i++){
                    data +=  transaksis.get(i).getProduk() + transaksis.get(i).getHarga() + "\n";
                }

                txtResult.setText(data);
            }
        });
    }

    private void simpanTransaksi() {
        String produk = edtProduk.getText().toString();
        String harga = edtHarga.getText().toString();
        Transaksi transaksi = new Transaksi(produk,Integer.valueOf(harga));

        if (produk.isEmpty() || harga.isEmpty()){

            Toast.makeText(this, "produk atau harga harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }
        //dijalan di background
        //build.getTransaksiDao().simpanTransaksi(transaksi);


        //add data pajak
        List<Pajak> pajaks = new ArrayList<>(Arrays.asList(

                new Pajak(1,500,"Tax 1"),
                new Pajak(1,500,"Tax 2"),
                new Pajak(1,500,"Tax 3"),
                new Pajak(1,500,"Tax 4")

        ));

        transaksi.setPajak(pajaks);

        new AsyncTask<Transaksi,Void , String>(){

            @Override
            protected String doInBackground(Transaksi... transaksis) {
                build.getTransaksiDao().simpanTransaksi(transaksis[0]);
                List<Transaksi> transactions = build.getTransaksiDao().getTransactions();
                System.out.println("size data :"  + transactions.size());
                String data = "";

                //get data di background
                //for (int i =0; i < transactions.size() ;i++){
                  //  data+=  transactions.get(i).getProduk() + transactions.get(i).getHarga() + "\n";
                //}


                return null;
            }

            //jika get data dari background
            /*@Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                txtResult.setText(s);
            }*/
        }.execute(transaksi);

    }
}
