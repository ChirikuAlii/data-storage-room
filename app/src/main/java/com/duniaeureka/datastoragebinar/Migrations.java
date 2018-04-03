package com.duniaeureka.datastoragebinar;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

/**
 * Created by chirikualii on 03/04/18.
 */

public class Migrations {


    // migrasi  database
    public static Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE transaksi ADD kategory TEXT");
        }
    };

}
