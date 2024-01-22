package com.example.dewey_20;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class VerTareas extends Fragment {

    ArrayList<String> listaTareas;
    ArrayList<datosTarea> listaDatosTarea;
    SQLiteTareas conn;
    ListView lvTareas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ver_tareas, container, false);
        lvTareas = view.findViewById(R.id.lvTareas);

        conn = new SQLiteTareas(getActivity().getApplicationContext(), "deweyTareas",null,1);



        consultarListaTareas();
        String[] nombres = getResources().getStringArray(R.array.tareasCreadas);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, listaTareas);
        lvTareas.setAdapter(adapter);
        lvTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String titulo = "Titulo Tarea: \n" + listaDatosTarea.get(i).getNombre();
                String descrip = "Detalles de la Tarea: \n" + listaDatosTarea.get(i).getDescripcion();
                String idTarea = String.valueOf(listaDatosTarea.get(i).getId());
                String status = String.valueOf(listaDatosTarea.get(i).getStatus());
                Intent intent = new Intent(getActivity(), completarTarea.class);
                intent.putExtra("tarea", titulo);
                intent.putExtra("descripcion",descrip);
                intent.putExtra("id",idTarea);
                intent.putExtra("status",status);

                startActivity(intent);
            }
        });

        return view;

    }

    private void consultarListaTareas() {
        try{
            SQLiteDatabase db = conn.getReadableDatabase();
            datosTarea tareas = null;
            listaDatosTarea = new ArrayList<datosTarea>();
            Cursor cursor = db.rawQuery("select * from tarea",null);
            while(cursor.moveToNext()) {
                tareas = new datosTarea();
                tareas.setId(cursor.getInt(0));
                tareas.setNombre(cursor.getString(1));
                tareas.setDescripcion(cursor.getString(2));
                tareas.setStatus(cursor.getString(3));
                tareas.setIdEmpleado(cursor.getInt(4));

                listaDatosTarea.add(tareas);
            }
            }catch(SQLiteAbortException e){
                Toast.makeText(getActivity().getApplicationContext(), "Error con la base de datos " + e, Toast.LENGTH_SHORT).show();
            }


        obtenerLista();
    }

    public void obtenerLista(){
        listaTareas =  new ArrayList<String>();

        for(int i = 0; i<listaDatosTarea.size();i++){
            listaTareas.add(listaDatosTarea.get(i).getId() + " - " + listaDatosTarea.get(i).getNombre() + " \n" + listaDatosTarea.get(i).getDescripcion() + "\n" + listaDatosTarea.get(i).getStatus());
        }
    }



}//class