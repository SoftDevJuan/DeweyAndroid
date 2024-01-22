package com.example.dewey_20;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteTareas extends SQLiteOpenHelper {
    public SQLiteTareas(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    //crea la tabla tarea con los campos: id, nombre, descripcion, status, idEmpleado.
    //el campo idEmpleado es null para si se desea crear una tarea sin asignar y poderla asignar mas tarde, aun asi para mostrar en el proyecto lo mejor es asignar la tarea desde que se crea
    @Override
    public void onCreate(SQLiteDatabase db){
        String createTables = "create table tarea(id integer primary key autoincrement," +
                "nombre text, descripcion  text, status text, idEmpleado integer);";
        db.execSQL(createTables);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //falta el codigo de upgrade
        db.execSQL("drop table if exists tarea");
        onCreate(db);
    }

    public void abrir(){
        this.getWritableDatabase();
    }

    public void cerrar(){
        this.close();
    }

    public Long insertar(String nom, String desc, String stat,int idEmp){
        ContentValues valores = new ContentValues();
        valores.put("nombre", nom);
        valores.put("descripcion", desc);
        valores.put("status", stat);
        valores.put("idEmpleado", idEmp);

        Long idReg = this.getWritableDatabase().insert("tarea",null,valores);

        return idReg;

    }




}//class
