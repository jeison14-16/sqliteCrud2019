package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.clases.coneccionDB;

import java.util.regex.Pattern;

public class Singup extends AppCompatActivity {
    private EditText fname, lapellido, ecorreo, pcontrasena, pcontrasena2;
    private String valcon = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{5,})";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //obtener id's
        fname =         findViewById(R.id.idfname);
        lapellido =     findViewById(R.id.apellido);
        ecorreo =       findViewById(R.id.correo);
        pcontrasena =   findViewById(R.id.contrasena);
        pcontrasena2 =  findViewById(R.id.contrasena2);
    }

    public void Signup(View view) {
        String name =           fname.getText().toString();
        String apellido =       lapellido.getText().toString();
        String correo =         ecorreo.getText().toString();
        String contrasena =     pcontrasena.getText().toString();
        String contrasena2 =    pcontrasena2.getText().toString();

        if(!name.isEmpty() && !apellido.isEmpty() && !correo.isEmpty() && !contrasena.isEmpty() && !contrasena2.isEmpty()) {
            if(!Patterns.EMAIL_ADDRESS.matcher(correo.trim()).matches()) {
                Toast.makeText(this, "Email Incorrect", Toast.LENGTH_SHORT).show();

            }else if(!Pattern.compile(valcon).matcher(contrasena).matches() || contrasena.length() < 5){
                Toast.makeText(this, "The password must contain letters numbers and symbols", Toast.LENGTH_SHORT).show();

            }else if(!contrasena.contentEquals(contrasena2)){
                Toast.makeText(this, "The passwords must be the same", Toast.LENGTH_SHORT).show();

            }else{
                String[] users1 = {name, apellido, correo, contrasena};
                coneccionDB conn = new coneccionDB(this, "market", null, 1);
                if(conn.register(users1)) {
                    Toast.makeText(this, "Register successfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }else {
                    Toast.makeText(this, "User already exist", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this, "There are empty fields", Toast.LENGTH_SHORT).show();
        }
    }



}
