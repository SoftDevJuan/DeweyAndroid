package com.example.dewey_20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener{

    EditText usuario, contra;
    Button login, registrar;

    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        usuario = findViewById(R.id.username);
        contra = findViewById(R.id.password);
        dao=new daoUsuario(this);
        login = findViewById(R.id.login);
        registrar = findViewById(R.id.registrar);
        login.setOnClickListener(this);
        registrar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                String u=usuario.getText().toString();
                String p=contra.getText().toString();
                if(u.equals("")&&p.equals("")){
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_SHORT).show();
                }else if (dao.login(u,p)==1){
                    Usuarios ux=dao.getUsuario(u,p);
                    Intent i2 = new Intent(Login.this, MainActivity.class);
                    i2.putExtra("id",ux.getId());
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this, "Usuario y/o password incorrectos", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.registrar:
                Intent i = new Intent(Login.this,Registrar.class);
                startActivity(i);
                break;
        }

    }
}