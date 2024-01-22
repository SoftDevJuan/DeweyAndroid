package com.example.dewey_20;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;




public class CrearTarea extends Fragment {

    EditText etNombre, etEmpleado, etDescripcion;

    String nuevoStatus = "Pendiente";
    Button btnCrear;
    Home home = new Home();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crear_tarea, container, false);
        etNombre = view.findViewById(R.id.tituloTarea);
        etEmpleado = view.findViewById(R.id.responsableTarea);
        etDescripcion = view.findViewById(R.id.detalleTarea);

        btnCrear = view.findViewById(R.id.btnCrear);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aqui ira el metodo para ejecutar SQL
                SQLiteTareas sqlite = new SQLiteTareas(getActivity().getApplicationContext(),"deweyTareas",null,1);
                Toast.makeText(getActivity().getApplicationContext(), "Nueva Tarea Creada! id:" + sqlite.insertar(etNombre.getText().toString(),etDescripcion.getText().toString(),"pendiente",Integer.parseInt(etEmpleado.getText().toString())), Toast.LENGTH_SHORT).show();
                sqlite.cerrar();

                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(R.id.frameContainer, home);

                // Commit the transaction
                transaction.commit();
            }

        });

        return view;
    }






}//class