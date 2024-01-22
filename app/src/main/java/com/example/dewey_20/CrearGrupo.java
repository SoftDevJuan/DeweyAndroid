package com.example.dewey_20;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ListAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CrearGrupo extends Fragment implements View.OnClickListener {

    EditText nombreGrupo, idGrupo;
    Spinner spIntegrantes;
    ListView lvIntegrantes;
    Button btnCrearGrupo, btnAgregar;
    Home home = new Home();
    List miLista = new ArrayList();
    daoUsuario dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crear_grupo, container, false);

        nombreGrupo = (EditText) view.findViewById(R.id.nombreGrupo);
        idGrupo = (EditText) view.findViewById(R.id.idGrupo);
        btnCrearGrupo = view.findViewById(R.id.btnCrearGrupo);
        btnCrearGrupo.setOnClickListener(this);
        btnAgregar = view.findViewById(R.id.btnAgrega);
        btnAgregar.setOnClickListener(this);

        dao = new daoUsuario(getActivity());


        //Código para el funcionamiento del spinner y listView -------------------------------------
        // Obtener referencia al Spinner
        spIntegrantes = view.findViewById(R.id.spIntegrantes);
        // Crear adaptador para el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.opciones_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Establecer el adaptador para el Spinner
        spIntegrantes.setAdapter(adapter);

        // Obtener referencia a la ListView
        lvIntegrantes = view.findViewById(R.id.lvIntegrantes);
        // Crear adaptador para la ListView
        ArrayAdapter<String> listAdapter;
        listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);

        // Establecer el adaptador para la ListView
        lvIntegrantes.setAdapter(listAdapter);


        // Agregar listener al Spinner
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = spIntegrantes.getSelectedItemPosition();
                String integranteSeleccionado = spIntegrantes.getSelectedItem().toString();
                String itemSeleccionado = spIntegrantes.getItemAtPosition(i).toString();
                if (listAdapter.getPosition(itemSeleccionado) == -1) {
                    // Si no existe, agregar el item seleccionado a la ListView
                    listAdapter.add(itemSeleccionado);
                } else {
                    // Si ya existe, mostrar un mensaje al usuario indicando que el valor ya fue agregado
                    Toast.makeText(getActivity(), "Esta persona ya ha sido integrada", Toast.LENGTH_SHORT).show();
                }
                miLista.add(integranteSeleccionado);
                adapter.notifyDataSetChanged();

            }
        });




        //Fin Código para el funcionamiento del spinner y listView ---------------------------------


        // Establece el listener del botón
        /* btnCrearGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(R.id.frameContainer, home);

                // Commit the transaction
                transaction.commit();
                Toast.makeText(getActivity(), "Grupo creado con exito", Toast.LENGTH_SHORT).show();
            }
        }); */



        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.btnCrearGrupo):
                Grupo g = new Grupo();
                g.setId(Integer.parseInt(idGrupo.getText().toString()));
                g.setNombreG(nombreGrupo.getText().toString());

                if (!g.isNull()) {
                    Toast.makeText(getActivity(), "ERROR: Campos vacios", Toast.LENGTH_SHORT).show();
                }else if(dao.insertGrupo(g)){
                    Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                    nombreGrupo.setText("");
                    idGrupo.setText("");

                }else{
                    Toast.makeText(getActivity(), "Debes llenar los campos", Toast.LENGTH_SHORT).show();
                }
                break;

    }
    }




    /*public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(), "dewey", null, 1);
        SQLiteDatabase dewey = admin.getWritableDatabase();

        String nombre = nombreGrupo.getText().toString();
        String identificador = idGrupo.getText().toString();
        String lider = liderGrupo.getText().toString();

        if (!nombre.isEmpty() && !identificador.isEmpty() && !lider.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("nombre", nombre);
            registro.put("identificador", identificador);
            registro.put("lider", lider);

            dewey.insert("grupos", null, registro);
            dewey.close();
            nombreGrupo.setText("");
            idGrupo.setText("");
            liderGrupo.setText("");
            Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getActivity(), "Debes llenar los campos", Toast.LENGTH_SHORT).show();
        }

    }*/

    /*public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(), "dewey", null, 1);
        SQLiteDatabase dewey = admin.getWritableDatabase();

        String identificador = idGrupo.getText().toString();
        if (!identificador.isEmpty()){
            Cursor fila = dewey.rawQuery("select Nombre, Lider from grupos where idGrupo ="+identificador, null);
            if (fila.moveToFirst()){
                nombreGrupo.setText(fila.getString(0));
                liderGrupo.setText(fila.getString(1));
                dewey.close();
            }else {
                Toast.makeText(getActivity(), "No existe el grupo", Toast.LENGTH_SHORT).show();
                dewey.close();
            }
        }else {
            Toast.makeText(getActivity(), "Ingresa el ID", Toast.LENGTH_SHORT).show();
        }
    }*/
}