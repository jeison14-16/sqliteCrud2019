package com.example.sqlite.clases;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.sqlite.UsuarioList;

import java.util.ArrayList;
import java.util.List;

public class coneccionDB extends SQLiteOpenHelper{

    public coneccionDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase market) {
        market.execSQL("create table users(id integer primary key autoincrement, " +
                "firsname text not null, lastname text not null, email text not null unique, " +
                "password text not null)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase market, int i, int i1) {
        market.execSQL("DROP TABLE IF EXISTS users");
        onCreate(market);
    }
    public boolean enter(String[] data){

        SQLiteDatabase conn = this.getReadableDatabase();

        Cursor cursor = conn.rawQuery("SELECT * from users WHERE email='" + data[0] + "' and " +
                "password='" + data[1] + "' limit 1", null);

        if(cursor.getCount() == 0){
            return false;
        }else{
            return true;
        }
    }

    public boolean register(String[] data){

        SQLiteDatabase market1=this.getWritableDatabase();
        try {
            //guardar datos
            ContentValues pack = new ContentValues();
            pack.put("firsname", data[0]);
            pack.put("lastname", data[1]);
            pack.put("email", data[2]);
            pack.put("password", data[3]);

            market1.insertOrThrow("users", null, pack);
            market1.close();

            return true;

        }catch(android.database.sqlite.SQLiteConstraintException ex){
            market1.close();
            return false;
        }
    }
}
