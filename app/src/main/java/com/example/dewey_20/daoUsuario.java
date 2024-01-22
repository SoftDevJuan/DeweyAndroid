package com.example.dewey_20;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class daoUsuario{
    Context c;
    Usuarios u;
    Grupo g;
    ArrayList<Usuarios> lista, listadoUsuarios;
    SQLiteDatabase sql;
    String bd="BDUsuarios";
    String tabla="create table if not exists usuarios(idUsuarios integer primary key autoincrement, Usuario text, Pass text, Nombre text, Apellidos text, Puesto text, Grupo text)";
    String tabla1="create table if not exists grupos(idGrupo integer primary key, NombreG text)";


    public daoUsuario(Context c){

        this.c=c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        sql.execSQL(tabla1);
        u=new Usuarios();
    }

    public boolean insertUsuario(Usuarios u){
        if(buscar(u.getUsuario())==0){
            ContentValues cv=new ContentValues();
            cv.put("Usuario" ,u.getUsuario());
            cv.put("Pass" ,u.getPassword());
            cv.put("Nombre" ,u.getNombre());
            cv.put("Apellidos" ,u.getApellido());
            cv.put("Puesto" ,u.getPuesto());
            return  (sql.insert("usuarios", null,cv)>0);
        }else{
            return false;
        }
    }
    public boolean insertGrupo(Grupo g){
        if(buscar(g.nombreG)==0){
            ContentValues cv=new ContentValues();
            cv.put("IdGrupo" ,g.idGrupo);
            cv.put("NombreG" ,g.nombreG);

            return  (sql.insert("grupos", null,cv)>0);
        }else{
            return false;
        }
    }
    public int buscar(String u){
        int x=0;
        lista=selectUsuarios();
        for (Usuarios us:lista) {
            if(us.getUsuario().equals(u)){
                x++;
            }

        }
        return x;
    }
    public ArrayList<Usuarios> selectUsuarios(){
        ArrayList<Usuarios> lista=new ArrayList<Usuarios>();
        lista.clear();
        Cursor cr=sql.rawQuery("select * from usuarios", null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                Usuarios u = new Usuarios();
                u.setId(cr.getInt(0));
                u.setUsuario(cr.getString(1));
                u.setPassword(cr.getString(2));
                u.setNombre(cr.getString(3));
                u.setApellido(cr.getString(4));
                u.setPuesto(cr.getString(5));
                lista.add(u);
            }while(cr.moveToNext());

        }
        return lista;
    }
    public int login(String u, String p){
        int a=0;
        Cursor cr=sql.rawQuery("select * from usuarios", null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                if(cr.getString(1).equals(u)&&cr.getString(2).equals(p)){
                    a++;
                }
            }while(cr.moveToNext());
        }
        return a;
    }
    public Usuarios getUsuario(String u, String p){
        lista=selectUsuarios();
        for (Usuarios us:lista) {
            if(us.getUsuario().equals(u)&&us.getPassword().equals(p)){
                return us;
            }
        }
        return null;
    }
    public Usuarios getUsuarioByid(int id){
        lista=selectUsuarios();
        for (Usuarios us:lista) {
            if(us.getId()==id){
                return us;
            }
        }
        return null;
    }





}//class
