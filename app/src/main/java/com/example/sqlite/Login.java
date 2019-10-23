package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.clases.coneccionDB;

public class Login extends AppCompatActivity {
    EditText idemail, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        idemail=    findViewById(R.id.idcorreo);
        contrasena= findViewById(R.id.password);
    }

    public void Signup(View view){
        startActivity(new Intent(getApplicationContext(),Singup.class));
    }

    public void Login (View view) {
        String email1 = idemail.getText().toString();
        String contra = contrasena.getText().toString();

        if (!email1.isEmpty() && !contra.isEmpty()) {
            if (Patterns.EMAIL_ADDRESS.matcher(email1.trim()).matches()) {
                String[] users1 = {email1, contra};
                coneccionDB conn = new coneccionDB(this, "market", null, 1);
                if (conn.enter(users1)) {
                    Toast.makeText(this, "Succesfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Email incorrect", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Fields empty", Toast.LENGTH_SHORT).show();
        }
    }
}
