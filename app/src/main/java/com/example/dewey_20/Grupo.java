package com.example.dewey_20;

public class Grupo {
    int idGrupo;
    String nombreG;

    public Grupo() {
    }
    public Grupo(int ids, String nombreg){
        nombreG = nombreg;
        idGrupo = ids;
    }

    public boolean isNull(){
        if(idGrupo==0&&nombreG.equals("")){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id=" + idGrupo +
                ", nombreG='" + nombreG + '\'' +
                '}';
    }



    public void setId(int id) {
        this.idGrupo = id;
    }

    public String getNombreG() {
        return nombreG;
    }

    public void setNombreG(String nombreG) {
        this.nombreG = nombreG;
    }
}
