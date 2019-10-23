package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sqlite.clases.coneccionDB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listUsuarios;
    private AdaptadorU adaptadorU;
    coneccionDB conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listUsuarios=findViewById(R.id.listR);
        adaptadorU = new AdaptadorU(this, obtenerDatos());

        listUsuarios.setAdapter(adaptadorU);
    }

    private ArrayList<UsuarioList> obtenerDatos (){
        ArrayList<UsuarioList> listItems = new ArrayList<>();

        conexion =new coneccionDB(this,"market",null,1);
        SQLiteDatabase market=conexion.getReadableDatabase();

        String[] campos = new String[] {"firsname", "lastname", "email"};

        Cursor c = market.query("users", campos, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                listItems.add(new UsuarioList(c.getString(0),c.getString(1),c.getString(2)));
            } while(c.moveToNext());
        }

        c.close();
        return listItems;
    }

    /*private ArrayList<UsuarioList> obtenerMujeres (){
        ArrayList<UsuarioList> listItems = new ArrayList<>();
        String [] gener = {"Mujer"};

        conexion=new coneccionDB(this,"market",null,1);
        SQLiteDatabase market=conexion.getReadableDatabase();

        Cursor cursor2=market.rawQuery("SELECT "+ Utilidades.CAMPO_NOMBRE + ", "+ Utilidades.CAMPO_APELLIDO + ", "+Utilidades.CAMPO_CONTRASENA +
                " FROM "+Utilidades.TABLA_USUARIO+ " WHERE " + Utilidades.CAMPO_GENERO+"=? ", gener);

        String[] campos = new String[] {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_APELLIDO, Utilidades.CAMPO_EMAIL};

        Cursor c = market.query(Utilidades.TABLA_USUARIO, campos, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                listItems.add(new UsuarioList(c.getString(0),c.getString(1),c.getString(2)));
            } while(c.moveToNext());
        }

        c.close();
        return listItems;
    }*/

    /*public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.options1,menu);
        return true;
    }*/

}
