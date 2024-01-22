package com.example.dewey_20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class completarTarea extends AppCompatActivity {

    String tarea, descripcion, idTarea,status;
    TextView tTarea, tDescripcion, tID, tStatus;
    Button btnCompletar;
    SQLiteTareas conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completar_tarea);
        tID = findViewById(R.id.tvID);
        tTarea = findViewById(R.id.tvTarea);
        tDescripcion = findViewById(R.id.tvDescripcion);
        btnCompletar = findViewById(R.id.btnCompletar);
        tStatus = findViewById(R.id.tvStatus);

        tarea = (String) getIntent().getSerializableExtra("tarea");
        descripcion = (String) getIntent().getSerializableExtra("descripcion");
        idTarea = (String) getIntent().getSerializableExtra("id");
        status = (String) getIntent().getSerializableExtra("status");

        tID.setText(idTarea);
        tTarea.setText(tarea);
        tDescripcion.setText(descripcion);
        tStatus.setText(status);

        btnCompletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarStatus();
                // Crea una instancia del Fragment que deseas mostrar
                VerTareas myFragment = new VerTareas();

                // Obtiene el FragmentManager de la Activity
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Crea una instancia de FragmentTransaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Reemplaza el contenedor del Fragment en el layout de la Activity con la instancia del Fragment que has creado
                fragmentTransaction.replace(android.R.id.content, myFragment);

                // Aplica los cambios
                fragmentTransaction.commit();
            }
        });
    }



    public void actualizarStatus(){
        conn = new SQLiteTareas(getApplicationContext(), "deweyTareas",null,1);
        SQLiteDatabase bd = conn.getWritableDatabase();
        String[] parametros = {tID.getText().toString()};
        ContentValues valores = new ContentValues();
        valores.put("status", "Completada");
        bd.update("tarea",valores,"id =?",parametros);
        Toast.makeText(getApplicationContext(),"Se completo la tarea", Toast.LENGTH_SHORT).show();
    }


}//Class