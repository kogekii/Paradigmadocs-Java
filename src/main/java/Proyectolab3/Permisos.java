package Proyectolab3;

import java.util.ArrayList;

public class Permisos {
    private String user;
    private ArrayList<String> acces;


    public Permisos(String u){
        this.user = u;
        this.acces = new ArrayList<>();
    }

    public void actualizar_permisos(String permiso){
        this.acces.add(permiso);
    }

    public String Getuser(){
        return user;
    }

    public Integer sizearray(){
        return acces.size();
    }
    public  ArrayList<String> Getacces(){
        return acces;
    }
}
