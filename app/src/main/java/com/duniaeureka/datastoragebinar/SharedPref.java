package com.duniaeureka.datastoragebinar;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chirikualii on 02/04/18.
 */

public class SharedPref {

    private SharedPreferences sp;
    private SharedPreferences.Editor spe;

    public static String KYE_IS_LOGIN = "key_login";

    public SharedPref(Context context){
        sp =context.getSharedPreferences("myapp",Context.MODE_PRIVATE);
        spe = sp.edit();
    }

    //sate to local data
    public void saveBoolean(boolean value , String key){

        spe.putBoolean(key,value);
        spe.commit();
    }

    public boolean getBoolean(String key){
        return sp.getBoolean(key,false);
    }
}
