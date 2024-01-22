package com.example.dewey_20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrar extends AppCompatActivity implements View.OnClickListener {
EditText Nombre, Apellidos, userReg, passReg;
Button Reg, cancelar;
daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        Nombre = findViewById(R.id.Nombre);
        Apellidos = findViewById(R.id.Apellidos);
        userReg = findViewById(R.id.userReg);
        passReg = findViewById(R.id.passReg);
        Reg = findViewById(R.id.Reg);
        cancelar = findViewById(R.id.cancelar);
        Reg.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        dao=new daoUsuario(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Reg:
                Usuarios u = new Usuarios();
                u.setNombre(Nombre.getText().toString());
                u.setApellido(Apellidos.getText().toString());
                u.setUsuario(userReg.getText().toString());
                u.setPassword(passReg.getText().toString());

                if(!u.isNull()){
                    Toast.makeText(this, "ERROR: Campos vacios ", Toast.LENGTH_SHORT).show();

                }else if (dao.insertUsuario(u)){
                    Toast.makeText(this, "Registro exitoso ", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Registrar.this,Login.class);
                    startActivity(i);
                    finish();

                }else{
                    Toast.makeText(this, "Usuario ya resgistrado ", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.cancelar:
                Intent i = new Intent(Registrar.this,Login.class);
                startActivity(i);
                finish();
                break;
        }
    }

    }
