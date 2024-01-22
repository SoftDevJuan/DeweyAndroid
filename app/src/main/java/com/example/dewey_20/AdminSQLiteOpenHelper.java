package com.example.dewey_20;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase dewey) {
        dewey.execSQL("create table grupos( idGrupo int NOT NULL, Nombre varchar(50) NOT NULL, Lider varchar(50) NOT NULL, primary key(idGrupo))");
        //dewey.execSQL("create table tarea( idTarea int NOT NULL primary key autoincrement, nombre varchar(50) NOT NULL, descripcion varchar(300) NOT NULL, status varchar(30) not null, idEmpleado int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists grupos");
        onCreate(db);
    }
}
